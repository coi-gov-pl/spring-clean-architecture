package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
@RequiredArgsConstructor
public enum Race {
  CAT(1), DOG(2), PIG(3);
  private final int value;
}
