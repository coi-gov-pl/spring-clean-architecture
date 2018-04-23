package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.04.18
 */
public interface PersonGateway {
  Optional<Person> findByNameAndSurname(String name, String surname);
}
