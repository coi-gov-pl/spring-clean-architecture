package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import java.time.Instant;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 20.12.16
 */
class RegisterNewPetRequestToPetMapper {
  private final EnumMapper<RegisterNewPetRequestModel.Race, Race> mapper;

  RegisterNewPetRequestToPetMapper(EnumMapper<RegisterNewPetRequestModel.Race, Race> mapper) {
    this.mapper = mapper;
  }

  Pet asPet(RegisterNewPetRequest request) {
    RegisterNewPetRequestModel.Race raceReq = request.getRace();
    Race race = mapper.map(raceReq);
    Pet pet = new Pet(
      request.getName(),
      race
    );
    Optional
      .ofNullable(request.getOwnership())
      .ifPresent(ownershipReq -> {
        Person person = new Person(
          ownershipReq.getName(),
          ownershipReq.getSurname()
        );
        Ownership ownershipEntity = new Ownership(
          pet, person, Instant.now()
        );
        pet.setOwnership(ownershipEntity);
      });
    return pet;
  }

}
