package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
@Getter
public final class RaceViewModel {
  private final String id;
  private final String name;
}
