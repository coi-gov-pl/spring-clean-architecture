package pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class Paginated<T> {
  private final PageInfo pageInfo;
  private final Iterable<T> elements;
}
