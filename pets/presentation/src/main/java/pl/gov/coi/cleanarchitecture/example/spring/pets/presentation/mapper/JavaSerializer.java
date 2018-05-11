package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.springframework.stereotype.Service;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
@Service
final class JavaSerializer implements Serializer {

  private static final int RADIX = 10;

  @Override
  public String serialize(Serializable serializable) {
    if (serializable instanceof Long) {
      return Long.toString((Long) serializable, RADIX);
    }
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(serializable);
      out.flush();
      return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(bos.toByteArray());
    } catch (IOException e) {
      throw new EidIllegalStateException("20180509:143244", e);
    }
  }

  @Override
  public Serializable unserialize(String serialized) throws IOException, ClassNotFoundException {
    Optional<Long> aLong = getLongFrom(serialized);
    if (aLong.isPresent()) {
      return aLong.get();
    } else {
      byte[] bytes = Base64
        .getUrlDecoder()
        .decode(serialized);
      try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
           ObjectInput in = new ObjectInputStream(bis)) {
        return (Serializable) in.readObject();
      }
    }
  }

  private Optional<Long> getLongFrom(String serialized) {
    Long value;
    try {
      value = Long.parseLong(serialized, RADIX);
      String asString = Long.toString(value, RADIX);
      if (!asString.equals(serialized)) {
        return Optional.empty();
      }
      return Optional.of(value);
    } catch (NumberFormatException ex) {
      return Optional.empty();
    }
  }
}
