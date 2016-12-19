package pl.gov.coi.example.spring.cleanarchitecture.core.presentation;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.usecase.Response;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface Presenter<V extends View> extends Response {
  V createView();
}
