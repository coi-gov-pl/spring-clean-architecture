package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.logic;

import org.springframework.stereotype.Service;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Race;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.gateway.PetsGateway;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetRequest;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetResponse;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetUseCase;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Service
class RegisterNewPetLogic implements RegisterNewPetUseCase {
  private final PetsGateway petsGateway;

  @Inject
  RegisterNewPetLogic(PetsGateway petsGateway) {
    this.petsGateway = petsGateway;
  }

  @Override
  public void execute(RegisterNewPetRequest request, RegisterNewPetResponse response) {
    String raceAsString = request.getRace();
    Race race = Race.valueOf(raceAsString);
    Pet pet = new Pet(request.getName(), race);
    request.getOwnership()
      .ifPresent(pet::setOwnership);
    Set<ConstraintViolation<Pet>> violations = Validation.buildDefaultValidatorFactory()
      .getValidator()
      .validate(pet);
    if (violations.isEmpty()) {
      Pet persisted = petsGateway.parsistNew(pet);
      response.setCreated(persisted);
    } else {
      response.setViolations(violations);
    }
  }
}
