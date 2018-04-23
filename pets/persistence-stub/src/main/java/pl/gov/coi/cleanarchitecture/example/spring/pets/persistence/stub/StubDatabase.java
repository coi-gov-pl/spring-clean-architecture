package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import java.util.Collection;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
public interface StubDatabase {
  Collection<Pet> getPets();
}
