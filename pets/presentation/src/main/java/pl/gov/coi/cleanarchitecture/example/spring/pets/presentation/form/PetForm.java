package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Race;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 20.12.16
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PetForm {
  private String petName;
  private String race;
  private String ownerName;
  private String ownerSurname;

  public Optional<Race> getRaceEnum() {
    for (Race raceEnum : Race.values()) {
      if (raceEnum.name().equalsIgnoreCase(this.race)) {
        return Optional.of(raceEnum);
      }
    }
    return Optional.empty();
  }
}
