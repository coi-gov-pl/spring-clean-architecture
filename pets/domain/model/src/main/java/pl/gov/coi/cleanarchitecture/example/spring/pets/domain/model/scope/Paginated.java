package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 */
@Getter
@RequiredArgsConstructor
public final class Paginated<T> {
  private final PageInfo pageInfo;
  private final Iterable<T> elements;
}
