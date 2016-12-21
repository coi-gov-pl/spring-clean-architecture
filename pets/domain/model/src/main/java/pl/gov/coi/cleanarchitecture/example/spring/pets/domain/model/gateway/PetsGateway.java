package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface PetsGateway {
  Iterable<Pet> getAllActive();
  Pet persistNew(Pet pet);
}
