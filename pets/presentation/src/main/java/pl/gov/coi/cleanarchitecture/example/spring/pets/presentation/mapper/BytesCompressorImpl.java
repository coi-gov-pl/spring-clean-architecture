package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
@Service
final class BytesCompressorImpl implements BytesCompressor {

  @Override
  public byte[] compress(byte[] bytes) throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         GZIPOutputStream gzos = new GZIPOutputStream(baos)) {
      gzos.write(bytes);
      gzos.finish();
      gzos.flush();
      return baos.toByteArray();
    }
  }

  @Override
  public byte[] inflate(byte[] bytes) throws IOException {
    try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
         GZIPInputStream gzis = new GZIPInputStream(bais)) {
      byte[] buffer = new byte[2048];
      try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        int len;
        while ((len = gzis.read(buffer)) > 0) {
          baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
      }
    }
  }
}
