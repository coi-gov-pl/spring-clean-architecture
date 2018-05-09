package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsRequest;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsUseCase;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper.EntityReferenceMapper;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetListController {

  private static final int DEFAULT_LIMIT = 10;
  private final FetchPetsUseCase fetchPetsUseCase;
  private final RacePresenter racePresenter;
  private final EntityReferenceMapper entityReferenceMapper;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String pets(Model model,
                     @Nullable
                     @RequestParam(value = "limit", required = false) Integer httpLimit) {
    FetchPetsRequest request = new FetchPetsRequest(
      Optional
        .ofNullable(httpLimit)
        .orElse(DEFAULT_LIMIT)
    );
    PetListPresenter presenter = new PetListPresenter(
      racePresenter, entityReferenceMapper
    );
    fetchPetsUseCase.execute(request, presenter);
    return presenter.createView()
      .bind(model)
      .getTemplatePath();
  }

}
