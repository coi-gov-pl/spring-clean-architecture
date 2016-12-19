package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import pl.gov.coi.example.spring.cleanarchitecture.core.presentation.SpringView;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.NewPetDTO;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(staticName = "create")
public class NewPetView implements SpringView<NewPetDTO> {
  private NewPetDTO viewModel;

  @Override
  public NewPetView setViewModel(NewPetDTO viewModel) {
    this.viewModel = viewModel;
    return this;
  }

  @Override
  public String getTemplatePath() {
    return "pet/new";
  }

  @Override
  public NewPetView bind(Model model) {
    model.addAttribute("races", viewModel.getRaceDTOs());
    Optional.ofNullable(viewModel.getViolations())
      .ifPresent(violations -> model.addAttribute("violations", violations));
    return this;
  }
}
