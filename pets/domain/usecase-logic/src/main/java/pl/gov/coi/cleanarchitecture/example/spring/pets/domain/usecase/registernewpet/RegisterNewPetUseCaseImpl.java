package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
class RegisterNewPetUseCaseImpl implements RegisterNewPetUseCase {
  private final PetsGateway petsGateway;
  private final PersonGateway personGateway;
  private final PetContractMapper mapper;
  private final Validator validator;

  @Override
  public void execute(RegisterNewPetRequest request,
                      RegisterNewPetResponse response) {
    Set<ConstraintViolation<RegisterNewPetRequest>> violations = validator
      .validate(request);
    if (violations.isEmpty()) {
      Pet pet = mapper.map(request.getPet(), this::ownershipAsPerson);
      petsGateway.persistNew(pet);
    } else {
      response.setViolations(toResponseModel(violations));
    }
  }

  private Optional<Person> ownershipAsPerson(Ownership ownership) {
    return personGateway
      .findByNameAndSurname(ownership.getName(), ownership.getSurname())
      .fetch(PersonFetchProfile.WITH_OWNERSHIPS);
  }

  private static Violation toViolation(ConstraintViolation<RegisterNewPetRequest> violation) {
    return new Violation(
      violation.getPropertyPath(),
      violation.getMessage()
    );
  }

  private static Iterable<Violation> toResponseModel(
    Iterable<ConstraintViolation<RegisterNewPetRequest>> violations) {
    return StreamSupport.stream(violations.spliterator(), false)
      .map(RegisterNewPetUseCaseImpl::toViolation)
      .collect(Collectors.toSet());
  }
}
