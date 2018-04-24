package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

import java.util.Collection;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = {
    OwnershipMapper.class,
    FormerOwnershipMapper.class
  }
)
interface PetMapper {
  PetData map(Pet pet, @Context CyclicGraphContext context);
  Pet map(PetData data, @Context CyclicGraphContext context);
  Collection<PetData> map(Collection<Pet> pets, @Context CyclicGraphContext context);

  @AfterMapping
  default void after(PetData petData, @MappingTarget Pet pet) {
    pet.supplierOfMetadata(() -> new MetadataImpl<>(Pet.class, petData));
  }
}
