package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl.PersonImpl;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl.PetImpl;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
final class PetsGatewayStub implements PetsGateway {
  private final Set<Pet> pets;

  PetsGatewayStub() {
    this.pets = new HashSet<>();

    Person ksuszynski = new PersonImpl("Krzysztof", "Suszyński");
    Person panderson = new PersonImpl("Pamela", "Anderson");
    Person llohan = new PersonImpl("Lindsay", "Lohan");

    pets.add(create(
      "Frodo",
      Race.DOG,
      ksuszynski,
      Instant.parse("2008-05-28T13:00:00.000Z")
    ));
    pets.add(create(
      "Kitie",
      Race.CAT,
      panderson,
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
      llohan,
      Instant.EPOCH
    ));
  }

  @Override
  public Iterable<Pet> getAllActive() {
    return Collections.unmodifiableSet(pets);
  }

  @Override
  public Pet persistNew(Pet pet) {
    ObjectSerializer<Pet> petObjectSerializer = new ObjectSerializer<>();
    Pet newOne = petObjectSerializer.refresh(pet);
    pets.add(pet);
    return newOne;
  }

  private static Pet create(String name, Race race, Person owner, Instant instant) {
    Pet pet = new PetImpl(name, race);
    pet.setOwner(owner);
    pet.getOwnership().ifPresent(ownership -> ownership.setFrom(instant));

    return pet;
  }

  private static Pet create(String name, Race race) {
    return new PetImpl(name, race);
  }
}
