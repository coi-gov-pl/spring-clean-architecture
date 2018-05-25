package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.view;

import org.springframework.ui.Model;
import pl.gov.coi.cleanarchitecture.presentation.View;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface SpringView<D> extends View<D> {
  String getTemplatePath();
  SpringView<D> bind(Model model);
  SpringView<D> setViewModel(D viewModel);
}
