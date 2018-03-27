package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl.PersonImpl;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl.PetImpl;
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
    PersonImpl ksuszynski = new PersonImpl("Krzysztof", "Suszyński");
    PersonImpl panderson = new PersonImpl("Pamela", "Anderson");
    PersonImpl llohan = new PersonImpl("Lindsay", "Lohan");

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

  private PetImpl create(String name, Race race, PersonImpl owner, Instant instant) {
    PetImpl pet = new PetImpl(name, race);
    Ownershssip ownership = new Ownershssip(pet, owner, instant);
    pet.setOwnership(ownership);
    return pet;
  }

  private PetImpl create(String name, Race race) {
    return new PetImpl(name, race);
  }
}
