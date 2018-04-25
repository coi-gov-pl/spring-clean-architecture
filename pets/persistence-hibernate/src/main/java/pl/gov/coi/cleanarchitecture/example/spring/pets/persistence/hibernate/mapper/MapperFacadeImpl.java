package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

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
  private final OwnershipMapper ownershipMapper;
  private final FormerOwnershipMapper formerOwnershipMapper;
  private final JpaMappingContextFactory jpaMappingContextFactory;

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
    MapperContext ctx = new MapperContext();
    JpaMappingContext jpaContext = jpaMappingContextFactory.produce(
      ctx,
      Mappings.builder()
        .addMapping(
          mapperFor(
            Pet.class, PetData.class,
            petMapper::updateFromPet
          )
        )
        .addMapping(
          mapperFor(
            Person.class, PersonData.class,
            personMapper::updateFromPerson
          )
        )
        .addMapping(
          mapperFor(
            Ownership.class, OwnershipData.class,
            ownershipMapper::updateFromOwnership
          )
        )
        .addMapping(
          mapperFor(
            FormerOwnership.class, FormerOwnershipData.class,
            formerOwnershipMapper::updateFromFormerOwnership
          )
        )
        .build()
    );

    ctx.addContext(new CyclicGraphContext());
    ctx.addContext(jpaContext);

    return ctx;
  }

  private <I, O> MapperContextMapping<I, O> mapperFor(Class<I> inputClass,
                                                      Class<O> outputClass,
                                                      TriConsumer<I, O, MapperContext> consumer) {
    return new MapperContextMapping<I, O>(inputClass, outputClass) {
      @Override
      public void accept(I input, O output, MapperContext context) {
        consumer.accept(input, output, context);
      }
    };
  }

  private abstract static class MapperContextMapping<I, O> extends Mapping<I, O, MapperContext> {

    MapperContextMapping(Class<I> sourceClass, Class<O> targetClass) {
      super(sourceClass, targetClass, MapperContext.class);
    }
  }
}
