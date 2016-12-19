package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Ownership;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetRequest;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetUseCase;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.presenter.NewPetPresenter;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Controller
@RequestMapping("/pets")
public class NewPetController {
  private final RegisterNewPetUseCase registerNewPetUseCase;

  @Inject
  public NewPetController(RegisterNewPetUseCase registerNewPetUseCase) {
    this.registerNewPetUseCase = registerNewPetUseCase;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String form(Model model) {
    return formHanding(
      model,
      NewPetPresenter.create()
    );
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String newOne(Model model,
                       @RequestParam("petName") String petName,
                       @RequestParam("race") String raceAsString) {
    RegisterNewPetRequest request = new RegisterNewPetRequestImpl(petName, raceAsString);
    NewPetPresenter presenter = NewPetPresenter.create();
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



  @RequiredArgsConstructor
  @Getter
  private static final class RegisterNewPetRequestImpl implements RegisterNewPetRequest {
    private final String name;
    private final String race;

    @Override
    public Optional<Ownership> getOwnership() {
      return Optional.empty();
    }
  }
}
