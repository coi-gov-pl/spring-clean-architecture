package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface RegisterNewPetResponseModel {
  @RequiredArgsConstructor
  @Getter
  class Violation {
    private final Object path;
    private final String message;
  }
}
