package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata;

import java.io.Serializable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
public interface Reference extends MetadataEntry<Serializable> {
  default boolean isEqualTo(Reference other) {
    return get().equals(other.get());
  }
}
