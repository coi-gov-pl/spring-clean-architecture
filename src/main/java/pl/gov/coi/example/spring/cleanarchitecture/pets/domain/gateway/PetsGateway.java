package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.gateway;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.gateway.Gateway;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface PetsGateway extends Gateway<Pet> {
  Iterable<Pet> getAll();

  Pet parsistNew(Pet pet);
}
