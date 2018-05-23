package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper.ReferencedPetLoader;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
      if (violations.isEmpty()) {
        EntityReference entityRef = request.getReference();
        Reference ref = entityRef::getReference;
        Pet pet = petContractMapper.map(
          request.getPet(),
          this::loadPersonByOwnership,
          new ReferencedPetLoader(
            ref, this::loadPetByReference
          )
        );
        petsGateway.update(ref, pet);
      } else {
        response.setViolations(mapViolations(violations));
      }
    };
  }

  private Optional<Pet> loadPetByReference(Reference reference) {
    return petsGateway
      .findByReference(reference)
      .fetch(PetFetchProfile.WITH_OWNERSHIPS);
  }

  private Optional<Person> loadPersonByOwnership(PetContract.Ownership ownership) {
    return personGateway
      .findByNameAndSurname(ownership.getName(), ownership.getSurname())
      .fetch(PersonFetchProfile.WITH_OWNERSHIPS);
  }

  private Iterable<Violation> mapViolations(Set<ConstraintViolation<UpdatePetRequest>> violations) {
    return violations.stream()
      .map(this::toResponseModel)
      .collect(Collectors.toList());
  }

  private Violation toResponseModel(ConstraintViolation<UpdatePetRequest> violation) {
    return new Violation(
      violation.getPropertyPath(),
      violation.getMessage()
    );
  }

  @Override
  public void execute(UpdatePetRequest request, UpdatePetResponse response) {
    updatePet(request).accept(response);
  }
}
