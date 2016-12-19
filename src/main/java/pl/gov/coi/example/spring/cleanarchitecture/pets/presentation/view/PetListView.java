package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view;

import org.springframework.ui.Model;
import pl.gov.coi.example.spring.cleanarchitecture.core.presentation.SpringView;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.PetListDTO;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public class PetListView implements SpringView<PetListDTO> {
  private static final String PETS_TEMPLATE = "pet/list";
  private PetListDTO petListDTO;

  @Override
  public String getTemplatePath() {
    return PETS_TEMPLATE;
  }

  @Override
  public PetListView bind(Model model) {
    if (petListDTO != null) {
      model.addAttribute("pets", petListDTO);
    }
    return this;
  }

  @Override
  public PetListView setViewModel(PetListDTO viewModel) {
    this.petListDTO = viewModel;
    return this;
  }
}
