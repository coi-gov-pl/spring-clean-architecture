package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Modified;

import java.time.Instant;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@RequiredArgsConstructor
final class InstantModified<T> implements Modified<T> {
  private final Supplier<Instant> instantSupplier;
  private final Supplier<Class<T>> classSupplier;

  @Override
  public Instant get() {
    return instantSupplier.get();
  }

  @Override
  public Class<T> type() {
    return classSupplier.get();
  }
}
