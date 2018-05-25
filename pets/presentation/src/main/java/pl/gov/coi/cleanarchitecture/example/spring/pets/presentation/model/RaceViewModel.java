package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
@Getter
public final class RaceViewModel {
  private final String id;
  private final String name;

  @Nullable
  public String isSelected(String value) {
    return id.equals(value) ? "true" : null;
  }
}
