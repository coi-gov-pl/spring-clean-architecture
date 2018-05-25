package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.usecase.Request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class UpdatePetRequest implements Request {
  @NotNull
  @Valid
  private final EntityReference reference;

  @NotNull
  @Valid
  private final PetContract pet;

}
