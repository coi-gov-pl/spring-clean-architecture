package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.constraint.Capitalized;
import pl.gov.coi.cleanarchitecture.usecase.Request;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class RegisterNewPetRequest implements Request {
  @NotNull
  @NotBlank
  @Size(max = 20)
  @Capitalized
  private final String name;
  @NotNull
  private final RegisterNewPetRequestModel.Race race;
  @Nullable
  @Valid
  private final RegisterNewPetRequestModel.Ownership ownership;
}
