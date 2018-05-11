package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Named
@Singleton
final class ClassNameEncoderImpl implements ClassNameEncoder {
  private static final int RADIX = 36;
  private final Map<Long, String> digestToClassName = new HashMap<>();

  @Override
  public String getReprForClassName(String className) {
    long digest = crc(className);
    String repr = getReprOfDigest(digest);
    if (!digestToClassName.containsKey(digest)) {
      digestToClassName.put(digest, className);
    }
    return repr;
  }

  @Override
  public String getClassNameFromRepr(String repr) {
    long digest = getDigestFromRepr(repr);
    return digestToClassName.get(digest);
  }

  private String getReprOfDigest(long digest) {
    return Long.toString(digest, RADIX);
  }

  private long getDigestFromRepr(String repr) {
    return Long.parseLong(repr, RADIX);
  }

  private long crc(String string) {
    CRC32 crc32 = new CRC32();
    crc32.update(string.getBytes(UTF_8));
    return crc32.getValue();
  }
}
