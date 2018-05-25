package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
public interface EntityReferenceMapper {
  <T extends HasMetadata<T>> TypedEntityReference<T> map(T hasMetadata);
}
