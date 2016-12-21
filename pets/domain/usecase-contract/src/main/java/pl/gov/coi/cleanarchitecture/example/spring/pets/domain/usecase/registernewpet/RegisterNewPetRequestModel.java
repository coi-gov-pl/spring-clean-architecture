package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface RegisterNewPetRequestModel {
  @RequiredArgsConstructor
  @Getter
  class Ownership {
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
  }

  enum Race {
    CAT, DOG, GUINEA_PIG
  }
}
