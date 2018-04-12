package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
final class PetsGatewayStub implements PetsGateway {
  private final Set<Pet> pets = new HashSet<>();

  @Override
  public Iterable<Pet> getAllActive() {
    return Collections.unmodifiableSet(pets);
  }

  @Override
  public Pet persistNew(Pet pet) {
    ObjectSerializer<Pet> petObjectSerializer = new ObjectSerializer<>();
    Pet newOne = petObjectSerializer.refresh(pet);
    pets.add(pet);
    return newOne;
  }

}
