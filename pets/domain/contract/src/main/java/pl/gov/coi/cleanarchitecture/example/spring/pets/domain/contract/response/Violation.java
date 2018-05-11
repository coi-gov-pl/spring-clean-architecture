package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@RequiredArgsConstructor
@Getter
public final class Violation {
  private final Object path;
  private final String message;
}
