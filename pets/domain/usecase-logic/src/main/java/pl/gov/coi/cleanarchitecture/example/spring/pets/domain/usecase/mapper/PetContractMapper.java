package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-14
 */
public interface PetContractMapper {
  Pet map(PetContract contract,
          PersonLoader personLoader,
          ReferencedPetLoader petLoader);
  Pet map(PetContract contract,
          PersonLoader personLoader);

  @RequiredArgsConstructor
  final class ReferencedPetLoader {
    private final Reference reference;
    private final PetLoader loader;

    Optional<Pet> load() {
      return loader.loadByReference(reference);
    }
  }
}
