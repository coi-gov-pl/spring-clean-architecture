package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = { OwnershipMapper.class, FormerOwnershipMapper.class }
)
public interface PetMapper {

  PetData map(Pet pet, @Context CyclicGraphContext context);
  Pet map(PetData data, @Context CyclicGraphContext context);
  List<PetData> map(List<Pet> pets, @Context CyclicGraphContext context);
}
