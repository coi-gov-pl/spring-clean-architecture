package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
public interface Serializer {
  String serialize(Serializable serializable);
  Serializable unserialize(String serialized) throws IOException, ClassNotFoundException;
}
