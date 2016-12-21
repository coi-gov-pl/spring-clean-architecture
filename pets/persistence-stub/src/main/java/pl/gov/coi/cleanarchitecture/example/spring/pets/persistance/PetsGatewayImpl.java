package pl.gov.coi.cleanarchitecture.example.spring.pets.persistance;

import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Service
class PetsGatewayImpl implements PetsGateway {
  private final Set<Pet> pets;

  PetsGatewayImpl() {
    this.pets = new HashSet<>();

    Person ksuszynski = new Person("Krzysztof", "Suszyński");

    pets.add(create(
      "Frodo",
      Race.DOG,
      ksuszynski,
      Instant.parse("2008-05-28T13:00:00.000Z")
    ));
    pets.add(create(
      "Kitie",
      Race.CAT,
      ksuszynski,
      Instant.parse("2005-04-11T11:00:00.000Z")
    ));
    pets.add(create(
      "Flamer",
      Race.CAT
    ));
    pets.add(create(
      "Tom",
      Race.CAT
    ));
    pets.add(create(
      "Hillburn",
      Race.DOG,
      ksuszynski,
      Instant.EPOCH
    ));
  }

  @Override
  public Iterable<Pet> getAllActive() {
    return pets;
  }

  @Override
  public Pet persistNew(Pet pet) {
    ObjectSerializer<Pet> petObjectSerializer = new ObjectSerializer<>();
    Pet newOne = petObjectSerializer.refresh(pet);
    pets.add(pet);
    return newOne;
  }

  private Pet create(String name, Race race, Person owner, Instant instant) {
    Pet pet = new Pet(name, race);
    Ownership ownership = new Ownership(pet, owner, instant);
    pet.setOwnership(ownership);
    return pet;
  }

  private Pet create(String name, Race race) {
    return new Pet(name, race);
  }
}
