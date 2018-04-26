package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-12
 */
@Service
@RequiredArgsConstructor
final class MapperFacadeImpl implements MapperFacade {
  private final PetMapper petMapper;
  private final PersonMapper personMapper;
  private final JpaMappingContextFactory jpaMappingContextFactory;
  private final List<UpdateMappingProvider<?, ?, ?>> updateMappingProviders;

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
    StoringMappingContext cyclicGraphCtx = new CyclicGraphContext();
    MapperContext ctx = new MapperContext();
    Mappings.MappingsBuilder mappingsBuilder = Mappings.builder();
    for (UpdateMappingProvider<?, ?, ?> mappingProvider : updateMappingProviders) {
      mappingsBuilder.addMapping(mappingProvider.provide());
    }
    JpaMappingContext jpaContext = jpaMappingContextFactory.produce(
      ctx, mappingsBuilder.build()
    );

    ctx.addContext(cyclicGraphCtx);
    ctx.addContext(jpaContext);

    return ctx;
  }

}
