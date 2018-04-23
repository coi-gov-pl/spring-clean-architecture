package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
final class SetStubDatabase implements StubDatabase {
  private final Set<Pet> pets = new LinkedHashSet<>();

  @Override
  public Collection<Pet> getPets() {
    return pets;
  }
}
