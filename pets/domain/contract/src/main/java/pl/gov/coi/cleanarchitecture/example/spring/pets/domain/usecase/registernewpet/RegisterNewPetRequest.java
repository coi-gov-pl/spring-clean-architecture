package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Race;
import pl.gov.coi.cleanarchitecture.usecase.Request;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
  @Valid
  private final PetContract pet;

  public RegisterNewPetRequest(String name,
                               Race race,
                               @Nullable Ownership ownership) {
    this(new PetContract(
      name, race, ownership
    ));
  }
}
