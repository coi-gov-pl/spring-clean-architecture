package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public interface RacePresenter {
  String present(PetContract.Race race);
  Iterable<RaceViewModel> presentAllRaces();
}
