package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 20.12.16
 */
final class RegisterNewPetRequestToPetMapper {
  private final EnumMapper<PetContract.Race, Race> mapper;

  RegisterNewPetRequestToPetMapper(EnumMapper<PetContract.Race, Race> mapper) {
    this.mapper = mapper;
  }

  Pet asPet(RegisterNewPetRequest request,
            Function<Ownership, Person> ownershipMapping) {
    PetContract petContract = request.getPet();
    PetContract.Race raceReq = petContract.getRace();
    Race race = mapper.map(raceReq);
    Pet pet = new Pet(
      petContract.getName(),
      race
    );
    Optional
      .ofNullable(petContract.getOwnership())
      .ifPresent(ownershipReq -> {
        Person person = ownershipMapping.apply(ownershipReq);
        pet.setOwner(person);
      });
    return pet;
  }

}
