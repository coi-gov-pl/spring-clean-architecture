package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import java.util.Collections;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(
  staticName = "create",
  access = AccessLevel.PACKAGE
)
class NewPetPresenter implements Presenter<NewPetView>, RegisterNewPetResponse {
  private final RacePresenter racePresenter;
  private Violations violations = createViolations(Collections.emptyList());

  @Override
  public NewPetView createView() {
    return NewPetView.create()
      .setViewModel(createModel());
  }

  private NewPetViewModel createModel() {
    return new NewPetViewModel(
      racePresenter.presentAllRaces(),
      violations
    );
  }

  @Override
  public void setViolations(Iterable<Violation> violations) {
    this.violations = createViolations(violations);
  }

  @Override
  public boolean isSuccessful() {
    return Optional.ofNullable(violations)
      .map(Violations::isSuccessful)
      .orElse(true);
  }

  private Violations createViolations(Iterable<Violation> violations) {

    return new Violations(violations, violation -> violation
      .getPath()
      .toString()
      .replaceFirst("^pet\\.", ""));
  }

}
