package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.usecase.Response;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface FetchPetsResponse extends Response {
  void setPets(Iterable<Pet> pets);
}
