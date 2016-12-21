package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequest;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetUseCase;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.RacePresenter;

import javax.inject.Inject;

import static pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet.NewPetView.NEW_PET_FORM_OBJECT;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Controller
@RequestMapping("/pets")
public class NewPetController {
  private final RegisterNewPetUseCase registerNewPetUseCase;
  private final RacePresenter racePresenter;

  @Inject
  public NewPetController(RegisterNewPetUseCase registerNewPetUseCase,
                          RacePresenter racePresenter) {
    this.registerNewPetUseCase = registerNewPetUseCase;
    this.racePresenter = racePresenter;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String form(Model model, @ModelAttribute(NEW_PET_FORM_OBJECT) NewPetForm form) {
    return formHanding(
      model,
      NewPetPresenter.create(racePresenter)
    );
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String newOne(Model model,
                       @ModelAttribute(NEW_PET_FORM_OBJECT) NewPetForm form) {

    RegisterNewPetRequest request = new RegisterNewPetRequest(
      form.getPetName(),
      form.getRaceEnum(),
      new RegisterNewPetRequestModel.Ownership(
        form.getOwnerName(),
        form.getOwnerSurname()
      )
    );
    NewPetPresenter presenter = NewPetPresenter.create(racePresenter);
    registerNewPetUseCase.execute(request, presenter);
    if (presenter.isSuccessful()) {
      return "redirect:../";
    } else {
      return formHanding(model, presenter);
    }
  }

  private String formHanding(Model model, NewPetPresenter presenter) {
    return presenter.createView()
      .bind(model)
      .getTemplatePath();
  }

}
