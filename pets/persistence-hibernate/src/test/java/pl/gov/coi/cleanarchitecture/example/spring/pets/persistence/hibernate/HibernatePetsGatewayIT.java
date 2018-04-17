package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import javax.inject.Inject;

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

  private PetsGateway petsGateway;

  @Inject
  void setPetsGateway(PetsGateway petsGateway) {
    this.petsGateway = petsGateway;
  }

  @Test
  public void testGetAllActive() {
    // given

    // when
    Iterable<Pet> pets = petsGateway.getAllActive();

    // then
    assertThat(pets).isNotNull();
    assertThat(pets).hasSize(5);
  }

  @Test
  public void testPersistNew() {
    // given

    // when

    // then
    Assert.fail("Not yet implemented");
  }
}
