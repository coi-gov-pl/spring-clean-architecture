package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter;

import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
@Service
public final class RacePresenterImpl implements RacePresenter {

  @Override
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

  @Override
  public Iterable<RaceViewModel> presentAllRaces() {
    return Arrays.stream(PetContract.Race.values())
      .filter(PetContract.Race::isSpecified)
      .map(this::toViewModel)
      .collect(Collectors.toList());
  }

  private RaceViewModel toViewModel(PetContract.Race race) {
    return new RaceViewModel(race.name(), present(race));
  }
}
