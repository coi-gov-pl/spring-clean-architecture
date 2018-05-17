package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
public enum PetFetchProfile implements FetchProfile<Pet> {
  SOLE, WITH_OWNERSHIPS, WITH_OWNER
}
