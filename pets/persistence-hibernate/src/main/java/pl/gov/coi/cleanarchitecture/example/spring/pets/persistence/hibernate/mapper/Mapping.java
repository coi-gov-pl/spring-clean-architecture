package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 25.04.18
 */
@Getter
@RequiredArgsConstructor
abstract class Mapping<I, O, C> implements TriConsumer<I, O, C> {
  private final Class<I> sourceClass;
  private final Class<O> targetClass;
  private final Class<C> contextClass;
}
