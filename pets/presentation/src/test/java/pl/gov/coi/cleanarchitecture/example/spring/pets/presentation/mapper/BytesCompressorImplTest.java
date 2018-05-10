package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
public class BytesCompressorImplTest {

  @Test
  public void test() throws IOException {
    // given
    String test = "A test string and a test string and another test string";
    BytesCompressor bytesCompressor = new BytesCompressorImpl();

    // when
    byte[] asBytes = test.getBytes(UTF_8);
    byte[] zipped = bytesCompressor.compress(asBytes);
    byte[] result = bytesCompressor.inflate(zipped);

    // then
    assertThat(new String(asBytes, UTF_8)).isEqualTo(test);
    assertThat(zipped.length).isLessThan(result.length);
  }

}
