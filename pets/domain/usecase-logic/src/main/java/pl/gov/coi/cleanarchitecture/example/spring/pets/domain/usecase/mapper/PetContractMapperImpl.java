package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-14
 */
@RequiredArgsConstructor
final class PetContractMapperImpl implements PetContractMapper {
  private final EnumMapper<PetContract.Race, Race> mapper;

  @Override
  public Pet map(PetContract contract,
                 PersonLoader personLoader,
                 ReferencedPetLoader petLoader) {
    return mapInternal(
      contract,
      personLoader,
      petLoader
    );
  }

  @Override
  public Pet map(PetContract contract,
                 PersonLoader personLoader) {
    return mapInternal(
      contract,
      personLoader,
      null
    );
  }

  private Pet mapInternal(PetContract contract,
                          PersonLoader personLoader,
                          @Nullable ReferencedPetLoader petLoader) {
    PetContract.Race raceReq = contract.getRace();
    Race race = mapper.map(raceReq);
    Pet pet = createPet(
      contract.getName(),
      race,
      petLoader
    );
    Optional
      .ofNullable(contract.getOwnership())
      .ifPresent(ownership -> {
        Person person = personLoader
          .loadFromContract(ownership)
          .orElse(newPerson(ownership));
        pet.setOwner(person);
      });
    return pet;
  }

  private Person newPerson(PetContract.Ownership ownership) {
    return new Person(ownership.getName(), ownership.getSurname());
  }

  private Pet createPet(String name,
                        Race race,
                        @Nullable ReferencedPetLoader petLoader) {
    if (petLoader == null) {
      return new Pet(
        name, race
      );
    } else {
      Pet pet = petLoader.load()
        .orElse(createPet(name, race));
      pet.setName(name);
      pet.setRace(race);
      return pet;
    }
  }

  private Pet createPet(String name, Race race) {
    return new Pet(
      name, race
    );
  }

}
