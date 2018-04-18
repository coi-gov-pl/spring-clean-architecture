package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope;

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
public final class PageInfo {
  private final Pagination pagination;
  private final long totalNumberOfElements;

  public boolean hasNextPage() {
    long pagedElements = getPagination().getElementsPerPage() * getPagination().getPageNumber();
    return pagedElements < getTotalNumberOfElements();
  }
}
