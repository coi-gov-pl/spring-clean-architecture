package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Created;

import java.time.Instant;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@RequiredArgsConstructor
final class InstantCreated implements Created {
  private final Supplier<Instant> instantSupplier;

  @Override
  public Instant get() {
    return instantSupplier.get();
  }

}
