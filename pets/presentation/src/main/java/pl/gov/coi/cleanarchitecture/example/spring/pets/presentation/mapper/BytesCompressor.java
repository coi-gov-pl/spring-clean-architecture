package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import java.io.IOException;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
public interface BytesCompressor {
  byte[] compress(byte[] bytes) throws IOException;
  byte[] inflate(byte[] bytes) throws IOException;
}
