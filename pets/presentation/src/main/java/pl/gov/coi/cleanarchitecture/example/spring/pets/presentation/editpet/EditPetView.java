package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.view.SpringView;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor(
  staticName = "create",
  access = AccessLevel.PACKAGE
)
final class EditPetView implements SpringView<EditPetViewModel> {
  static final String FORM_OBJECT = "editPet";
  private EditPetViewModel viewModel;

  @Override
  public String getTemplatePath() {
    return "pet/edit";
  }

  @Override
  public SpringView<EditPetViewModel> bind(Model model) {
    model.addAttribute("races", viewModel.getRaceViewModel());
    model.addAttribute("violations", viewModel.getViolations());
    if (!model.containsAttribute(FORM_OBJECT)) {
      model.addAttribute(FORM_OBJECT, viewModel.getPet());
    }
    return this;
  }

  @Override
  public SpringView<EditPetViewModel> setViewModel(EditPetViewModel viewModel) {
    this.viewModel = viewModel;
    return this;
  }
}
