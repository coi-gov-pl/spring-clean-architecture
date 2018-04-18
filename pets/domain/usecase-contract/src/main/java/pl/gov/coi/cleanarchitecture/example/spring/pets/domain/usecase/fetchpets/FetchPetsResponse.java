package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import pl.gov.coi.cleanarchitecture.usecase.Response;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface FetchPetsResponse extends Response {
  void setPets(Iterable<FetchPetsResponseModel.Pet> pets);
  void setPageInfo(long totalNumberOfElements, int elementsPerPage, int pageNumber);
}
