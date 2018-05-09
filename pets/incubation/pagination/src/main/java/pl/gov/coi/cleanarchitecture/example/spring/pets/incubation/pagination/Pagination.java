package pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 */
@Getter
@ToString
@EqualsAndHashCode
public final class Pagination {
  private final int elementsPerPage;
  private final int pageNumber;

  public Pagination(int elementsPerPage, int pageNumber) {
    checkArgument(elementsPerPage >= 1, "20180418:105600");
    checkArgument(pageNumber >= 1, "20180418:105707");
    this.elementsPerPage = elementsPerPage;
    this.pageNumber = pageNumber;
  }

  public Pagination(int elementsPerPage) {
    this(elementsPerPage, 1);
  }

  public Pagination next() {
    return new Pagination(
      getElementsPerPage(),
      getPageNumber() + 1
    );
  }

  public Pagination previous() {
    int number = getPageNumber() < 2 ? 1 : getPageNumber();
    return new Pagination(getElementsPerPage(), number);
  }

  public Pagination first() {
    return new Pagination(getElementsPerPage(), 1);
  }
}
