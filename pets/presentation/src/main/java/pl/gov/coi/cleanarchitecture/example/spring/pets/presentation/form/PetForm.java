package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Race;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalArgumentException;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 20.12.16
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
public final class PetForm {
  private String petName;
  private String race;
  private String ownerName;
  private String ownerSurname;

  public Race getRaceEnum() {
    for (Race raceEnum : Race.values()) {
      if (raceEnum.name().equalsIgnoreCase(this.race)) {
        return raceEnum;
      }
    }
    throw new EidIllegalArgumentException(
      new Eid("20180509:110843"),
      "Invalid race value: %s",
      this.race
    );
  }
}
