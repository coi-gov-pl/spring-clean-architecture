package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
public interface Metadata<T> {
  <I, D extends MetadataEntry<I>> Optional<D> get(Class<D> metadataClass);
  Class<T> type();
}
