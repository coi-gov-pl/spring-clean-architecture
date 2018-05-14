package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form.PetForm;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.view.SpringView;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import java.util.ArrayList;
import java.util.Optional;

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

  private Violations violations = createViolations(new ArrayList<>());

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

  @Override
  public SpringView<EditPetViewModel> createView() {
    return EditPetView.create()
      .setViewModel(createViewModel());
  }

  private EditPetViewModel createViewModel() {
    return new EditPetViewModel(
      createForm(pet),
      racePresenter.presentAllRaces(),
      violations
    );
  }

  private EditPetForm createForm(PetContract pet) {
    PetForm.PetFormBuilder builder = PetForm.builder()
      .race(pet.getRace().name())
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

  private Violations createViolations(Iterable<Violation> violations) {

    return new Violations(violations, violation -> violation
      .getPath()
      .toString()
      .replaceFirst("^data\\.", ""));
  }
}
