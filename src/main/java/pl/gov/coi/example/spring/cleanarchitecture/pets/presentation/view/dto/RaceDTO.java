package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
@Getter
public class RaceDTO {
  private final String id;
  private final String name;
}
