package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import org.springframework.ui.Model;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.SpringView;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
class PetListView implements SpringView<PetListViewModel> {
  private static final String PETS_TEMPLATE = "pet/list";
  private PetListViewModel petListViewModel;

  @Override
  public String getTemplatePath() {
    return PETS_TEMPLATE;
  }

  @Override
  public PetListView bind(Model model) {
    checkNotNull(petListViewModel, "20161220:114623");
    model.addAttribute("pets", petListViewModel.getList());
    model.addAttribute("petCount", petListViewModel.getCount());
    return this;
  }

  @Override
  public PetListView setViewModel(PetListViewModel viewModel) {
    this.petListViewModel = checkNotNull(viewModel, "20161220:115152");
    return this;
  }
}
