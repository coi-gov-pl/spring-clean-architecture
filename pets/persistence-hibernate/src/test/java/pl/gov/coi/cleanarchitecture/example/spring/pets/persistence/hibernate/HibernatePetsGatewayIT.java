package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;


import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleData;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.04.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("hibernate")
@DataJpaTest
@ContextConfiguration(classes = TestPersistenceConfiguration.class)
public class HibernatePetsGatewayIT {

  @Inject
  private PetsGateway petsGateway;
  @Inject
  private PersonGateway personGateway;
  @Inject
  private ExampleData exampleData;
  @Inject
  private EntityManager entityManager;

  @Test
  public void testGetPets() {
    // given
    Pagination pagination = new Pagination(3);
    exampleData.createExamples();

    // when
    Paginated<Pet> pets = petsGateway.getPets(pagination);
    PageInfo pageInfo = pets.getPageInfo();

    // then
    assertThat(pets).isNotNull();
    assertThat(pageInfo).isNotNull();
    assertThat(pageInfo.getTotalNumberOfElements()).isEqualTo(5);
    assertThat(pageInfo.hasNextPage()).isTrue();
    assertThat(pets.getElements()).hasSize(3);

    // getting pet which should have former owners list but not fetched (lazy) by gateway
    Pet pet = pets.getElements().iterator().next();
    try {
      pet.getFormerOwners().iterator();
      failBecauseExceptionWasNotThrown(LazyInitializationException.class);
    } catch (LazyInitializationException ex) {
      assertThat(ex).hasMessage("Trying to use uninitialized collection for type: " +
        "List<FormerOwnershipData>. You need to fetch this collection before using it, " +
        "for ex. using JOIN FETCH in JPQL. This exception prevents lazy loading n+1 problem.");
    }
  }

  @Test
  public void testPersistNew() {
    // given
    Pet pet = new Pet("Alice", Race.PIG);

    // when
    long beforeCount = countPets();
    petsGateway.persistNew(pet);
    long afterCount = countPets();

    // then
    assertThat(afterCount - beforeCount).isEqualTo(1);
    assertThat(beforeCount).isEqualTo(0);
  }

  @Test
  public void testPersistNewWithOwner() {
    // given
    exampleData.createExamples();
    Pet pet = new Pet("Johnie", Race.CAT);
    Person person = personGateway
      .findByNameAndSurname("Lindsay", "Lohan")
      .fetch(PersonFetchProfile.WITH_OWNERSHIPS)
      .orElseThrow(HibernatePetsGatewayIT::loadError);
    pet.setOwner(person);

    // when
    long beforeCount = countPets();
    petsGateway.persistNew(pet);
    long afterCount = countPets();

    // then
    assertThat(afterCount - beforeCount).isEqualTo(1);
    assertThat(beforeCount).isEqualTo(5);

    person = personGateway
      .findByNameAndSurname("Lindsay", "Lohan")
      .fetch(PersonFetchProfile.WITH_OWNERSHIPS)
      .orElseThrow(HibernatePetsGatewayIT::loadError);
    assertThat(person.getOwnershipCount())
      .isEqualTo(2);
  }

  private static RuntimeException loadError() {
    return new EidIllegalStateException(new Eid("20180424:162330"));
  }

  private long countPets() {
    return (long) entityManager
      .createQuery("SELECT count(p.id) FROM PetData p")
      .getSingleResult();
  }
}
