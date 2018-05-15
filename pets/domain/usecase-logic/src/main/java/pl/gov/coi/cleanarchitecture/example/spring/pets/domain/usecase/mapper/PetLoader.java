package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 15.05.18
 */
public interface PetLoader {
  Optional<Pet> loadByReference(Reference reference);
}
