package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface PetsGateway {
  Optional<Pet> findByReference(Reference reference);
  Paginated<Pet> getPets(Pagination pagination);
  void persistNew(Pet... pet);
}
