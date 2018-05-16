package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.wavesoftware.utils.mapstruct.jpa.CompositeContext;
import pl.wavesoftware.utils.mapstruct.jpa.MapStructContextProvider;

import java.util.Collection;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-12
 */
@Service
@RequiredArgsConstructor
final class MapperFacadeImpl implements MapperFacade {
  private final PetMapper petMapper;
  private final PersonMapper personMapper;
  private final MapStructContextProvider<CompositeContext> contextProvider;

  @Override
  public Pet map(PetData data) {
    return petMapper.map(data, contextProvider.createNewContext());
  }

  @Override
  public Collection<PetData> map(Collection<Pet> pets) {
    return petMapper.map(pets, contextProvider.createNewContext());
  }

  @Override
  public Person map(PersonData data) {
    return personMapper.map(data, contextProvider.createNewContext());
  }

  @Override
  public UpdatingPetData update(PetData petData) {
    return pet ->
      petMapper.updateFromPet(
        pet, petData, contextProvider.createNewContext()
      );
  }

}
