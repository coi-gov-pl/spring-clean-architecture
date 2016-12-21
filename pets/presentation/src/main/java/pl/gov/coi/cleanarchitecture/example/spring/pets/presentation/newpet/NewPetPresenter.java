package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponseModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.RacePresenter;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(
  staticName = "create",
  access = AccessLevel.PACKAGE
)
class NewPetPresenter implements Presenter<NewPetView>, RegisterNewPetResponse {
  @Nullable
  private List<RegisterNewPetResponseModel.Violation> violations;
  private final RacePresenter racePresenter;

  @Override
  public NewPetView createView() {
    return NewPetView.create()
      .setViewModel(createModel());
  }

  private NewPetViewModel createModel() {
    return new NewPetViewModel(
      Arrays.stream(RegisterNewPetRequestModel.Race.values())
        .map(this::toViewModel)
        .collect(Collectors.toList()),
      violations
    );
  }

  private RaceViewModel toViewModel(RegisterNewPetRequestModel.Race race) {
    return new RaceViewModel(race.name(), racePresenter.present(race));
  }

  @Override
  public void setViolations(Iterable<RegisterNewPetResponseModel.Violation> violations) {
    this.violations = StreamSupport
      .stream(violations.spliterator(), false)
      .collect(Collectors.toList());
  }

  @Override
  public boolean isSuccessful() {
    return Optional.ofNullable(violations)
      .map(List::isEmpty)
      .orElse(true);
  }
}
