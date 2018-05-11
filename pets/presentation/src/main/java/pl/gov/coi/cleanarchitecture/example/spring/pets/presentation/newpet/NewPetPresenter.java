package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(
  staticName = "create",
  access = AccessLevel.PACKAGE
)
class NewPetPresenter implements Presenter<NewPetView>, RegisterNewPetResponse {
  private Violations violations = new Violations(Collections.emptyList());
  private final RacePresenter racePresenter;

  @Override
  public NewPetView createView() {
    return NewPetView.create()
      .setViewModel(createModel());
  }

  private NewPetViewModel createModel() {
    return new NewPetViewModel(
      Arrays.stream(PetContract.Race.values())
        .map(this::toViewModel)
        .collect(Collectors.toList()),
      violations
    );
  }

  private RaceViewModel toViewModel(PetContract.Race race) {
    return new RaceViewModel(race.name(), racePresenter.present(race));
  }

  @Override
  public void setViolations(Iterable<Violation> violations) {
    this.violations = new Violations(violations);
  }

  @Override
  public boolean isSuccessful() {
    return Optional.ofNullable(violations)
      .map(Violations::isSuccessful)
      .orElse(true);
  }

}
