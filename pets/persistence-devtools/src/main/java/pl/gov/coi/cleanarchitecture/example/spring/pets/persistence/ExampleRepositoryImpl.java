package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExampleRepositoryImpl implements ExampleRepository {
  private final PetsGateway gateway;
  private final EntityManager entityManager;

  @Override
  public Examples createExamples() {
    log.info("Creating example data into persistence layer...");
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

    Iterable<Pet> managed = gateway.persistNew(pets.toArray(new Pet[0]));

    entityManager.flush();
    entityManager.clear();

    return new ExamplesImpl(managed);
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

  private static final class ExamplesImpl implements Examples {
    private final Map<PetExample, Pet> petRecords = new EnumMap<>(PetExample.class);
    private final Map<PersonExample, Person> personRecords = new EnumMap<>(PersonExample.class);

    ExamplesImpl(Iterable<Pet> managed) {
      for (Pet pet : managed) {
        labelOf(pet)
          .ifPresent(petExample ->
            petRecords.put(petExample, pet)
          );
        ownerOf(pet)
          .ifPresent(entry -> {
            PersonExample example = entry.getKey();
            if (!personRecords.containsKey(example)) {
              personRecords.put(example, entry.getValue());
            }
          });
      }
    }

    private Optional<Entry<PersonExample, Person>> ownerOf(Pet pet) {
      return pet.getOwnership()
        .map(Ownership::getPerson)
        .flatMap(this::labelOf);
    }

    private Optional<Entry<PersonExample, Person>> labelOf(Person person) {
      String fname = person.getName() + " " + person.getSurname();
      switch (fname) {
        case "Krzysztof Suszyński":
          return entry(PersonExample.K_SUSZYNSKI, person);
        case "Pamela Anderson":
          return entry(PersonExample.P_ANDERSON, person);
        case "Lindsay Lohan":
          return entry(PersonExample.L_LOHAN, person);
        default:
          return Optional.empty();
      }
    }

    private Optional<PetExample> labelOf(Pet pet) {
      switch (pet.getName()) {
        case "Frodo":
          return Optional.of(PetExample.FRODO);
        case "Kitie":
          return Optional.of(PetExample.KITIE);
        case "Tom":
          return Optional.of(PetExample.TOM);
        case "Flamer":
          return Optional.of(PetExample.FLAMER);
        case "Hillburn":
          return Optional.of(PetExample.HILLBURN);
        default:
          return Optional.empty();
      }
    }

    private static Optional<Entry<PersonExample, Person>> entry(PersonExample example,
                                                                Person person) {
      return Optional.of(new SimpleEntry<>(example, person));
    }

    @Override
    public Pet getPet(PetExample petExample) {
      return checkNotNull(petRecords.get(petExample), "20180518:131506");
    }

    @Override
    public Person getPerson(PersonExample personExample) {
      return checkNotNull(personRecords.get(personExample), "20180518:131448");
    }
  }
}
