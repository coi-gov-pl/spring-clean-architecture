package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.interactor.LoadPetInteractor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet.UpdatePetUseCase;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper.EntityReferenceMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIndexOutOfBoundsException;

import static pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet.EditPetView.FORM_OBJECT;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
final class EditPetController {
  private final UpdatePetUseCase useCase;
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
    // TODO: implement
    throw new UnsupportedOperationException("not yet implemented");
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
