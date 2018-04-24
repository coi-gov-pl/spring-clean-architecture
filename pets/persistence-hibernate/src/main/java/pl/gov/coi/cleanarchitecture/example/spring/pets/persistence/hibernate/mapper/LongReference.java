package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
@RequiredArgsConstructor
final class LongReference<T> implements Reference<Long, T> {

  private final Supplier<Long> idSupplier;
  private final Supplier<Class<T>> classSupplier;

  @Override
  public Long get() {
    return idSupplier.get();
  }

  @Override
  public Class<T> type() {
    return classSupplier.get();
  }
}
