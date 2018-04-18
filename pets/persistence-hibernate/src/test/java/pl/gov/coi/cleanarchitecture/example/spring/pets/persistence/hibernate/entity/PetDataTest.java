package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 */
public class PetDataTest {

  @Test
  public void testToString() {
    // given
    Instant now = Instant.ofEpochSecond(1524070953);
    Instant before = now.minus(13, ChronoUnit.DAYS);
    PersonData person = new PersonData();
    person.setName("John");
    person.setSurname("Goodman");
    OwnershipData ownership = new OwnershipData();
    ownership.setFrom(Date.from(now));
    ownership.setPerson(person);
    PetData pet = new PetData();
    pet.setRace(Race.PIG);
    pet.setName("Johnie");
    pet.setOwnership(ownership);
    ownership.setPet(pet);
    person.getOwnerships().add(ownership);


    PersonData former = new PersonData();
    former.setName("John");
    former.setSurname("Cusack");
    FormerOwnershipData formerOwnership = new FormerOwnershipData();
    formerOwnership.setPerson(former);
    formerOwnership.setFrom(Date.from(before));
    formerOwnership.setTo(Date.from(now));
    formerOwnership.setPet(pet);

    pet.getFormerOwners().add(formerOwnership);

    // when
    String repr = pet.toString();

    // then
    assertThat(repr).isNotEmpty();
    assertThat(repr).isEqualTo(
      "<PetData name=\"Johnie\", race=PIG, ownership=<OwnershipData pet=(↻), " +
        "person=<PersonData name=\"John\", surname=\"Goodman\">, " +
        "from=Wed Apr 18 19:02:33 CEST 2018>, formerOwners=[<FormerOwnershipData pet=(↻), " +
        "person=<PersonData name=\"John\", surname=\"Cusack\">, " +
        "from=Thu Apr 05 19:02:33 CEST 2018, to=Wed Apr 18 19:02:33 CEST 2018>]>"
    );
  }
}
