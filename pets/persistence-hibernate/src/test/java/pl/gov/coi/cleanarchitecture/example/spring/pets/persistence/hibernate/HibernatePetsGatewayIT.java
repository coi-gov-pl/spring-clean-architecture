package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleData;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

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
  }

  private long countPets() {
    return (long) entityManager
      .createQuery("SELECT count(p.id) FROM PetData p")
      .getSingleResult();
  }
}