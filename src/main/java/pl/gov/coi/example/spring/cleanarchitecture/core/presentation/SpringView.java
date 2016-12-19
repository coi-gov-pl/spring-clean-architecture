package pl.gov.coi.example.spring.cleanarchitecture.core.presentation;

import org.springframework.ui.Model;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface SpringView<D> extends View<D> {
  String getTemplatePath();
  SpringView<D> bind(Model model);
}
