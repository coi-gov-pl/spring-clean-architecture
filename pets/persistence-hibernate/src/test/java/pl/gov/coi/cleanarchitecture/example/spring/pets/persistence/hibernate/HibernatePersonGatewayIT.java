package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleRepository;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 25.04.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("hibernate")
@DataJpaTest
@ContextConfiguration(classes = TestPersistenceConfiguration.class)
public class HibernatePersonGatewayIT {

  @Inject
  private PersonGateway personGateway;
  @Inject
  private ExampleRepository exampleRepository;

  @Test
  public void testFindByNameAndSurname() {
    // given
    exampleRepository.createExamples();

    // when
    Optional<Person> optionalPerson = personGateway
      .findByNameAndSurname("Pamela", "Anderson")
      .fetch(PersonFetchProfile.SOLE);

    // then
    assertThat(optionalPerson).isPresent();
    Person person = optionalPerson.orElseThrow(HibernatePersonGatewayIT::loadError);
    assertThat(person)
      .extracting(Person::getName)
      .allMatch(o -> o.equals("Pamela"));
  }

  private static RuntimeException loadError() {
    return new EidIllegalStateException(new Eid("20180425:124036"));
  }
}
