package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Service
@RequiredArgsConstructor
class ExampleDataImpl implements ExampleData {
  private final PetsGateway gateway;
  private final EntityManager entityManager;

  @Override
  public void createExamples() {
    Person ksuszynski = new Person("Krzysztof", "Suszyński");
    Person panderson = new Person("Pamela", "Anderson");
    Person llohan = new Person("Lindsay", "Lohan");

    List<Pet> pets = new ArrayList<>();

    pets.add(create(
      "Frodo",
      Race.DOG,
      ksuszynski,
      Instant.parse("2008-05-28T13:00:00.000Z")
    ));
    Pet kitie = create(
      "Kitie",
      Race.CAT,
      llohan,
      Instant.parse("2005-04-11T11:00:00.000Z")
    );
    kitie.setOwner(panderson);
    pets.add(kitie);
    pets.add(create(
      "Flamer",
      Race.PIG
    ));
    pets.add(create(
      "Tom",
      Race.CAT,
      llohan,
      Instant.parse("2011-02-11T13:56:01.000Z")
    ));
    pets.add(create(
      "Hillburn",
      Race.DOG,
      llohan,
      Instant.EPOCH
    ));

    gateway.persistNew(pets.toArray(new Pet[0]));

    entityManager.flush();
    entityManager.clear();
  }

  private Pet create(String name, Race race, Person owner, Instant instant) {
    Pet pet = create(name, race);
    Ownership ownership = new Ownership(pet, owner, instant);
    pet.setOwnership(ownership);
    owner.addOwnership(ownership);
    return pet;
  }

  private Pet create(String name, Race race) {
    return new Pet(name, race);
  }
}
