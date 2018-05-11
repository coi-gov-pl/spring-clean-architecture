package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form.PetForm;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.view.SpringView;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor
final class EditPetPresenter implements Presenter<SpringView<EditPetViewModel>>,
  UpdatePetResponse {

  private final String reference;
  private final PetContract pet;
  private final RacePresenter racePresenter;

  @Nullable
  private Violations violations;

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

  @Override
  public SpringView<EditPetViewModel> createView() {
    return EditPetView.create()
      .setViewModel(createViewModel());
  }

  private EditPetViewModel createViewModel() {
    return new EditPetViewModel(
      createForm(pet),
      presentRaces(),
      violations
    );
  }

  private Iterable<RaceViewModel> presentRaces() {
    return Arrays.stream(PetContract.Race.values())
      .map(this::toViewModel)
      .collect(Collectors.toList());
  }

  private EditPetForm createForm(PetContract pet) {
    PetForm.PetFormBuilder builder = PetForm.builder()
      .race(racePresenter.present(pet.getRace()))
      .petName(pet.getName());
    Optional.ofNullable(pet.getOwnership())
      .ifPresent(ownership -> {
        builder.ownerName(ownership.getName());
        builder.ownerSurname(ownership.getSurname());
      });
    PetForm petForm = builder.build();
    return new EditPetForm(
      reference, petForm
    );
  }

  private RaceViewModel toViewModel(PetContract.Race race) {
    return new RaceViewModel(race.name(), racePresenter.present(race));
  }
}
