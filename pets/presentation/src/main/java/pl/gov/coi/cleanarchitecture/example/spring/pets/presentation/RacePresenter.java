package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
@Component
public class RacePresenter {
  public String present(PetContract.Race race) {
    switch (race) {
      case CAT:
        return "Cat";
      case DOG:
        return "Dog";
      case GUINEA_PIG:
        return "Guinea pig :-)";
      default:
        return "Unknown race ;-O";
    }
  }
}
