package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.MetadataEntry;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.Record;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
@RequiredArgsConstructor
final class MetadataImpl<T> implements Metadata<T> {
  private final Class<T> type;
  private final Record record;

  @Override
  public <D extends MetadataEntry<?, T>> Optional<D> get(Class<D> dataClass) {
    return Optional.empty();
  }

  @Override
  public Class<T> type() {
    return type;
  }
}
