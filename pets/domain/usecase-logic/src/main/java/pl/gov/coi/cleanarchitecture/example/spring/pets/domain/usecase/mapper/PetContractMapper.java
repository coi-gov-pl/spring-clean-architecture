package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import java.util.function.Function;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-14
 */
public interface PetContractMapper {
  Pet map(PetContract contract,
          Function<PetContract.Ownership, Person> personLoader);
  PetContract map(Pet pet);
}
