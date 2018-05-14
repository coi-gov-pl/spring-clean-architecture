package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor
final class UpdatePetUseCaseImpl implements UpdatePetUseCase {
  private final PetContractMapper petContractMapper;
  private final PetsGateway petsGateway;
  private final PersonGateway personGateway;
  private final Validator validator;

  @Override
  public Consumer<UpdatePetResponse> updatePet(UpdatePetRequest request) {
    return response -> {
      Set<ConstraintViolation<UpdatePetRequest>> violations =
        validator.validate(request);
      // TODO: implement this
      if (violations.isEmpty()) {
        throw new UnsupportedOperationException("not yet implemented");
      }
      throw new UnsupportedOperationException("not yet implemented");
    };
  }

  private Person loadPerson(PetContract.Ownership ownership) {
    return null;
  }

  @Override
  public void execute(UpdatePetRequest request, UpdatePetResponse response) {
    updatePet(request).accept(response);
  }
}
