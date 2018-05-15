package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 15.05.18
 */
public interface PersonLoader {
  Optional<Person> loadFromContract(Ownership ownership);
}
