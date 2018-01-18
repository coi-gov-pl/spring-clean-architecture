package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Component
final class ExampleDataImpl implements ExampleData {
  @Override
  public void createExamples(PetsGateway gateway) {
    Person ksuszynski = new Person("Krzysztof", "Suszyński");
    Person panderson = new Person("Pamela", "Anderson");
    Person llohan = new Person("Lindsay", "Lohan");

    gateway.persistNew(create(
      "Frodo",
      Race.DOG,
      ksuszynski,
      Instant.parse("2008-05-28T13:00:00.000Z")
    ));
    gateway.persistNew(create(
      "Kitie",
      Race.CAT,
      panderson,
      Instant.parse("2005-04-11T11:00:00.000Z")
    ));
    gateway.persistNew(create(
      "Flamer",
      Race.CAT
    ));
    gateway.persistNew(create(
      "Tom",
      Race.CAT
    ));
    gateway.persistNew(create(
      "Hillburn",
      Race.DOG,
      llohan,
      Instant.EPOCH
    ));
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
