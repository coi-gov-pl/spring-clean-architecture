package pl.gov.coi.example.spring.cleanarchitecture.core.presentation;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface View<D> {
  View<D> setViewModel(D viewModel);
}
