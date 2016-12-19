package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsRequest;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsUseCase;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.presenter.PetListPresenter;

import javax.inject.Inject;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Controller
@RequestMapping("/pets")
public class PetListController {

  private final FetchPetsUseCase fetchPetsUseCase;

  @Inject
  public PetListController(FetchPetsUseCase fetchPetsUseCase) {
    this.fetchPetsUseCase = fetchPetsUseCase;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String pets(Model model) {
    FetchPetsRequest request = new FetchPetsRequestImpl(10);
    PetListPresenter presenter = new PetListPresenter();
    fetchPetsUseCase.execute(request, presenter);
    return presenter.createView()
      .bind(model)
      .getTemplatePath();
  }

  @RequiredArgsConstructor
  @Getter
  private static final class FetchPetsRequestImpl implements FetchPetsRequest {
    private final int maxElements;
  }
}
