package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface FetchPetsResponseModel {

  @RequiredArgsConstructor
  @Getter
  class Pet {
    private final String name;
    private final RegisterNewPetRequestModel.Race race;
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
