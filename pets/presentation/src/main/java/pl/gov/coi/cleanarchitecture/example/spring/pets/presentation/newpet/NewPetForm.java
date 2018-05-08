package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Race;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 20.12.16
 */
@Setter
@Getter
@RequiredArgsConstructor
final class NewPetForm {
  private String petName;
  private String race;
  private String ownerName;
  private String ownerSurname;

  @Nullable
  Race getRaceEnum() {
    for (Race raceEnum : Race.values()) {
      if (raceEnum.name().equalsIgnoreCase(this.race)) {
        return raceEnum;
      }
    }
    return null;
  }
}
