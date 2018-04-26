package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Created;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.MetadataEntry;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Modified;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.Record;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
  public <I, D extends MetadataEntry<I>> Optional<D> get(Class<D> metadataClass) {
    MetadataEntryProvider<T, D> provider = new MetadataEntryProvider<>(type, record);
    return provider.get(metadataClass);
  }

  @Override
  public Class<T> type() {
    return type;
  }

  @Getter(AccessLevel.PRIVATE)
  @RequiredArgsConstructor
  private static final class MetadataEntryProvider<T, D extends MetadataEntry<?>> {
    private final Class<T> type;
    private final Record record;

    Optional<D> get(Class<D> dataClass) {
      List<Optional<D>> out = new ArrayList<>();
      out.add(processForReference(dataClass));
      out.add(processForCreated(dataClass));
      out.add(processForModified(dataClass));
      return out.stream()
        .reduce(this::accumulateOpts)
        .orElse(Optional.empty());
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<D> accumulateOpts(Optional<D> first, Optional<D> second) {
      return first.isPresent() ? first : second;
    }

    private Optional<D> processForReference(Class<D> dataClass) {
      if (dataClass.isAssignableFrom(Reference.class)) {
        @SuppressWarnings("unchecked")
        D ref = (D) new LongReference(record::getId);
        return Optional.of(ref);
      }
      return Optional.empty();
    }

    private Optional<D> processForCreated(Class<D> dataClass) {
      if (dataClass.isAssignableFrom(Created.class)) {
        @SuppressWarnings("unchecked")
        D ref = (D) new InstantCreated(this::getCreated);
        return Optional.of(ref);
      }
      return Optional.empty();
    }

    private Optional<D> processForModified(Class<D> dataClass) {
      if (dataClass.isAssignableFrom(Modified.class)) {
        @SuppressWarnings("unchecked")
        D ref = (D) new InstantModified(this::getModified);
        return Optional.of(ref);
      }
      return Optional.empty();
    }

    private Instant getModified() {
      return getRecord().getModified().toInstant();
    }

    private Instant getCreated() {
      return getRecord().getCreated().toInstant();
    }
  }

}
