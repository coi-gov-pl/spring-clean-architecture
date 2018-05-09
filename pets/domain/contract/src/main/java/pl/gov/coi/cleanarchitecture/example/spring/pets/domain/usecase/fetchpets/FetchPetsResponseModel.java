package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface FetchPetsResponseModel {

  @RequiredArgsConstructor
  @Getter
  class Pet {
    private final EntityReference reference;
    private final String name;
    private final PetContract.Race race;
    @Nullable
    private final Owner owner;

  }

  @RequiredArgsConstructor
  @Getter
  class Owner {
    private final String name;
    private final String surname;
  }
}
