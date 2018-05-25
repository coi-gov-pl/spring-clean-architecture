package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.constraint.Capitalized;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface RegisterNewPetRequestModel {
  @RequiredArgsConstructor
  @Getter
  @ToString
  class Ownership {
    @NotBlank
    @Capitalized
    private final String name;
    @NotBlank
    @Capitalized
    private final String surname;
  }

  enum Race {
    CAT, DOG, GUINEA_PIG
  }
}
