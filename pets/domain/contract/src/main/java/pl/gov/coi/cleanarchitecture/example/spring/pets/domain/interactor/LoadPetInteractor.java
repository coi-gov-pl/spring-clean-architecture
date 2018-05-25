package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.interactor;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public interface LoadPetInteractor {
  Optional<PetContract> findPetByReference(EntityReference reference);
}
