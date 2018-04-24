package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

import java.util.Arrays;
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
  private final JpaMappingContext jpaMappingContext;

  @Override
  public Pet map(PetData data) {
    return petMapper.map(data, newContext());
  }

  @Override
  public Collection<PetData> map(Collection<Pet> pets) {
    return petMapper.map(pets, newContext());
  }

  @Override
  public Person map(PersonData data) {
    return personMapper.map(data, newContext());
  }

  private MapperContext newContext() {
    return new MapperContext(Arrays.asList(
      jpaMappingContext,
      new CyclicGraphContext()
    ));
  }
}
