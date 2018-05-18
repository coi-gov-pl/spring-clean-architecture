package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;


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
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleRepository;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.Examples;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.Examples.PersonExample;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.Examples.PetExample;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;
import pl.wavesoftware.utils.mapstruct.jpa.collection.LazyInitializationException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

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
  private ExampleRepository exampleRepository;
  @Inject
  private EntityManager entityManager;
  @Inject
  private QueryInterceptor queryInterceptor;

  @Test
  public void testGetPets() {
    // given
    Pagination pagination = new Pagination(3);
    exampleRepository.createExamples();

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
    exampleRepository.createExamples();
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

  @Test
  public void testFindByReferenceFetchingSoleProfile() {
    // given
    Examples examples = exampleRepository.createExamples();
    Reference reference = examples
      .getPet(PetExample.KITIE)
      .getMetadata()
      .get(Reference.class)
      .orElseThrow(HibernatePetsGatewayIT::loadError);
    queryInterceptor.clear();

    // when
    Optional<Pet> pet = petsGateway.findByReference(reference)
      .fetch(PetFetchProfile.SOLE);

    // then
    assertThat(pet).isPresent();
    pet.ifPresent(p -> {
      assertThat(p.getName()).isEqualTo("Kitie");
      assertThat(p.getFormerOwners().toString())
        .isEqualTo("UninitializedList<FormerOwnershipData>");
    });
    // TODO: Can it be loaded in single query?
    assertThat(queryInterceptor.getExecutedQueries())
      .hasSize(2);
  }

  @Test
  public void testFindByReferenceFetchingWithOwnershipsProfile() {
    // given
    Examples examples = exampleRepository.createExamples();
    Reference reference = examples
      .getPet(PetExample.KITIE)
      .getMetadata()
      .get(Reference.class)
      .orElseThrow(HibernatePetsGatewayIT::loadError);
    queryInterceptor.clear();

    // when
    Optional<Pet> pet = petsGateway.findByReference(reference)
      .fetch(PetFetchProfile.WITH_OWNERSHIPS);

    // then
    assertThat(pet).isPresent();
    pet.ifPresent(p -> {
      assertThat(p.getName()).isEqualTo("Kitie");
      assertThat(p.getFormerOwners().toString())
        .contains("<FormerOwnership person=<Person name=\"Lindsay\", surname=\"Lohan\"");
    });
    // TODO: Can it be loaded in single query?
    assertThat(queryInterceptor.getExecutedQueries())
      .hasSize(2);
  }

  @Test
  public void testUpdatePetByChangingOwner() {
    // given
    Examples examples = exampleRepository.createExamples();
    Pet kitie = examples.getPet(PetExample.KITIE);
    Person llohan = examples.getPerson(PersonExample.L_LOHAN);
    Reference reference = kitie
      .getMetadata()
      .get(Reference.class)
      .orElseThrow(HibernatePetsGatewayIT::loadError);
    queryInterceptor.clear();

    // when
    kitie.setOwner(llohan);
    petsGateway.update(reference, kitie);

    // then
    // TODO: Can it be changed in 2 queries?
    assertThat(queryInterceptor.getExecutedQueries())
      .hasSize(9);
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
