package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.SpringView;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(
  staticName = "create",
  access = AccessLevel.PACKAGE
)
class NewPetView implements SpringView<NewPetViewModel> {
  static final String NEW_PET_FORM_OBJECT = "newPet";
  private NewPetViewModel viewModel;

  @Override
  public NewPetView setViewModel(NewPetViewModel viewModel) {
    this.viewModel = viewModel;
    return this;
  }

  @Override
  public String getTemplatePath() {
    return "pet/new";
  }

  @Override
  public NewPetView bind(Model model) {
    model.addAttribute("races", viewModel.getRaceViewModel());
    Optional.ofNullable(viewModel.getViolations())
      .ifPresent(violations -> model.addAttribute("violations", violations));
    return this;
  }
}
