package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsRequest;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsUseCase;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.RacePresenter;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Controller
@RequestMapping("/pets")
public class PetListController {

  private static final int DEFAULT_LIMIT = 10;
  private final FetchPetsUseCase fetchPetsUseCase;
  private final RacePresenter racePresenter;

  @Inject
  public PetListController(FetchPetsUseCase fetchPetsUseCase,
                           RacePresenter racePresenter) {
    this.fetchPetsUseCase = fetchPetsUseCase;
    this.racePresenter = racePresenter;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String pets(Model model,
                     @Nullable
                     @RequestParam(value = "limit", required = false) Integer httpLimit) {
    FetchPetsRequest request = new FetchPetsRequest(
      Optional
        .ofNullable(httpLimit)
        .orElse(DEFAULT_LIMIT)
    );
    PetListPresenter presenter = new PetListPresenter(racePresenter);
    fetchPetsUseCase.execute(request, presenter);
    return presenter.createView()
      .bind(model)
      .getTemplatePath();
  }

}
