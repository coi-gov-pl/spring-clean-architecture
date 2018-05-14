package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.interactor.LoadPetInteractor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetRequest;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetUseCase;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form.PetForm;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper.EntityReferenceMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;
import pl.wavesoftware.eid.exceptions.EidIndexOutOfBoundsException;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet.EditPetView.FORM_OBJECT;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
final class EditPetController {
  private final UpdatePetUseCase updatePetUseCase;
  private final EntityReferenceMapper entityReferenceMapper;
  private final LoadPetInteractor loadPetInteractor;
  private final RacePresenter racePresenter;

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String form(Model model,
                     @RequestParam(value = "reference") String reference) {
    PetContract pet = getPetByReference(reference);
    return formHanding(
      model,
      new EditPetPresenter(reference, pet, racePresenter)
    );
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String edit(Model model,
                     @ModelAttribute(FORM_OBJECT) EditPetForm form) {
    EntityReference reference = entityReferenceMapper
      .map(form.getReference())
      .orElseThrow(() -> new EidIllegalStateException(new Eid("20180514:223428")));
    PetContract pet = getPetFromForm(form.getData());
    UpdatePetRequest request = new UpdatePetRequest(
      reference, pet
    );
    Consumer<UpdatePetResponse> response = updatePetUseCase.updatePet(request);
    EditPetPresenter presenter = new EditPetPresenter(
      form.getReference(),
      pet,
      racePresenter
    );
    response.accept(presenter);
    if (presenter.isSuccessful()) {
      return "redirect:../";
    } else {
      return formHanding(model, presenter);
    }
  }

  private static boolean isNotBlank(@Nullable String input) {
    return input != null && !input.trim().isEmpty();
  }

  private PetContract getPetFromForm(PetForm form) {
    PetContract.PetContractBuilder builder = PetContract.builder();
    builder.name(form.getPetName())
      .race(
        form.getRaceEnum().orElse(PetContract.Race.UNSPECIFIED)
      );
    if (isNotBlank(form.getOwnerName()) || isNotBlank(form.getOwnerSurname())) {
      builder.ownership(new PetContract.Ownership(
        form.getOwnerName(), form.getOwnerSurname()
      ));
    }
    return builder.build();
  }

  private String formHanding(Model model, EditPetPresenter presenter) {
    return presenter.createView()
      .bind(model)
      .getTemplatePath();
  }

  private PetContract getPetByReference(String repr) {
    return entityReferenceMapper
      .map(repr)
      .flatMap(loadPetInteractor::findPetByReference)
      .orElseThrow(
        () -> new EidIndexOutOfBoundsException(new Eid("20180511:195020"))
      );
  }
}
