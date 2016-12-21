package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel.Ownership;
import pl.gov.coi.cleanarchitecture.usecase.Request;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
  private final String name;
  @NotNull
  @NotBlank
  private final RegisterNewPetRequestModel.Race race;
  @Nullable
  private final Ownership ownership;
}
