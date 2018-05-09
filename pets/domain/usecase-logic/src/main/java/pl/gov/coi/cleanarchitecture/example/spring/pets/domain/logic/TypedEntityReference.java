package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
public interface TypedEntityReference<T extends HasMetadata<T>> extends EntityReference {
  @Override
  Class<T> getType();
}
