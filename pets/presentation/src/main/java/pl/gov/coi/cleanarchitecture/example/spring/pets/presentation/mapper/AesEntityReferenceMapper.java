package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalArgumentException;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
@Service
@Slf4j
final class AesEntityReferenceMapper implements EntityReferenceMapper {

  // Use nerdfonts.com to see this sign :-P
  private static final String REPR_SPLITTER = "";
  private static final String EMPTY_STRING = "";

  /**
   * Operation modes
   */
  public enum Mode {
    LIMITED, UNLIMITED
  }

  private final CharSequence key;
  private final ClassLocator classLocator;
  private final Serializer serializer;
  private final ClassNameEncoder classNameEncoder;

  private Mode mode = Mode.UNLIMITED;
  private Cipher cipherInst;
  private Map<Mode, SecretKeySpec> keySpec = new EnumMap<>(Mode.class);
  private Cipher encryptCipher;
  private Cipher decryptCipher;

  /**
   * Default constructor. You can decide to use only {@link Mode#LIMITED} mode if needed.
   *
   * @param key          a secret key used to cipher/decipher entities representations,
   *                     should be reasonably safely generated.
   * @param tryUnlimited should try to use <code>UNLIMITED</code> mode?
   * @param classLocator a class that can find a class by name
   */
  AesEntityReferenceMapper(@Named(Constants.KEY) CharSequence key,
                           @Named(Constants.TRY_UNLIMITED) boolean tryUnlimited,
                           ClassLocator classLocator,
                           Serializer serializer,
                           ClassNameEncoder classNameEncoder) {
    this.key = key;
    this.classLocator = classLocator;
    this.serializer = serializer;
    this.classNameEncoder = classNameEncoder;
    if (!tryUnlimited) {
      mode = Mode.LIMITED;
    }
    initiate();
  }

  @Override
  public CharSequence map(EntityReference reference) {
    return pack(
      reference.getType(),
      reference.getReference()
    );
  }

  @Override
  public Optional<EntityReference> map(CharSequence repr) {
    if (repr.equals(EMPTY_STRING)) {
      return Optional.empty();
    }
    return Optional.of(
      unpack(repr)
    );
  }

  private CharSequence pack(Serializable type,
                            @Nullable Serializable id) {
    if (id == null) {
      return EMPTY_STRING;
    }
    String classNameRepr = getClassNameRepr(type);
    try {
      String repr = repr(id, classNameRepr);
      byte[] input = repr.getBytes(UTF_8);
      Cipher cipher = getEncrypt();
      byte[] output;
      output = cipher.doFinal(input);
      return encode(output);
    } catch (NoSuchAlgorithmException |
      NoSuchPaddingException |
      IllegalBlockSizeException |
      BadPaddingException |
      IllegalArgumentException ex) {
      throw new EidIllegalArgumentException("20140131:003856", ex);
    }
  }

  private EntityReference unpack(CharSequence repr) {
    try {
      Cipher cipher = getDecrypt();
      byte[] input = decode(repr);
      String output = new String(cipher.doFinal(input), UTF_8);
      int idx = output.indexOf(REPR_SPLITTER);
      String serializedId = output.substring(0, idx);
      String type = output.substring(idx + REPR_SPLITTER.length());
      String fqcn = getFqcnForType(type);
      return new EntityReferenceImpl(
        classLocator.locateClassByName(fqcn),
        serializer.unserialize(serializedId)
      );
    } catch (GeneralSecurityException |
      ClassNotFoundException |
      NumberFormatException |
      IOException ex) {
      throw new EidIllegalArgumentException("20180509:134806", ex);
    }
  }

  private String repr(Serializable id,
                      String classNameRepr) {
    return serializer.serialize(id) + REPR_SPLITTER + classNameRepr;
  }

  private CharSequence encode(byte[] bytes) {
    return Base64
      .getUrlEncoder()
      .withoutPadding()
      .encodeToString(bytes)
      .trim();
  }

  private byte[] decode(CharSequence charSequence) {
    try {
      return Base64
        .getUrlDecoder()
        .decode(charSequence.toString());
    } catch (IllegalArgumentException ex) {
      throw new EidIllegalStateException("20180509:132318", ex);
    }
  }

  private String getClassNameRepr(Serializable type) {
    if (type instanceof Class) {
      return classNameEncoder.getReprForClassName(
        Class.class.cast(type).getName()
      );
    }
    throw new EidIllegalStateException(
      new Eid("20180509:143750"), "Unsupported type: %s", type.getClass()
    );
  }

  private String getFqcnForType(String type) {
    return classNameEncoder.getClassNameFromRepr(type);
  }

  private void initiate() {
    try {
      getEncrypt();
      getDecrypt();
    } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
      throw new EidIllegalStateException("20140131:003954", ex);
    }
  }

  private Cipher getEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {
    if (encryptCipher == null) {
      encryptCipher = tryToInit(Cipher.ENCRYPT_MODE);
    }
    cipherInst = encryptCipher;
    return cipherInst;
  }

  private Cipher getDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {
    if (decryptCipher == null) {
      decryptCipher = tryToInit(Cipher.DECRYPT_MODE);
    }
    cipherInst = decryptCipher;
    return cipherInst;
  }

  private Cipher tryToInit(int cmode) throws NoSuchAlgorithmException, NoSuchPaddingException {
    Cipher cipher;
    try {
      cipher = getAesCipher();
      cipher.init(cmode, getKey());
      return cipher;
    } catch (InvalidKeyException ex) {
      mode = Mode.LIMITED;
      cipherInst = null;
      log.warn(
        "Using LIMITED cipher mode (AES 128 bits). Install Java Cryptography " +
        "Extension (JCE) Unlimited Strength Jurisdiction Policy Files for better " +
        "encryption (AES 256 bits)"
      );
      try {
        cipher = getAesCipher();
        cipher.init(cmode, getKey());
        return cipher;
      } catch (InvalidKeyException ex1) {
        throw new EidIllegalStateException("20140131:003922", ex1);
      }
    }
  }

  private static Cipher getAesCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance("AES");
  }

  private SecretKeySpec getKey() throws NoSuchAlgorithmException {
    if (keySpec.containsKey(mode)) {
      return keySpec.get(mode);
    }
    int bits;
    if (mode == Mode.UNLIMITED) {
      bits = 256;
    } else {
      bits = 128;
    }
    keySpec.put(mode, new SecretKeySpec(calulateKey(bits), "AES"));
    return keySpec.get(mode);
  }

  private byte[] calulateKey(int bits) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] bytes = digest
      .digest((key + this.getClass().getName())
      .getBytes(UTF_8));
    int rounds = 5;
    for (int i = 0; i < rounds; i++) {
      bytes = digest.digest(bytes);
    }
    int bytesNum = bits / 8;
    String base64 = Base64
      .getUrlEncoder()
      .withoutPadding()
      .encodeToString(bytes)
      .substring(0, bytesNum);
    return base64.getBytes(UTF_8);
  }

  @Getter
  @RequiredArgsConstructor
  private static final class EntityReferenceImpl implements EntityReference {
    private final Serializable type;
    private final Serializable reference;
  }

}
