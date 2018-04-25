package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = { OwnershipMapper.class }
)
public interface PersonMapper {
  PersonData map(Person person, @Context MapperContext context);
  Person map(PersonData data, @Context MapperContext context);
  void updateFromPerson(Person person,
                        @MappingTarget PersonData data,
                        @Context MapperContext context);

  @AfterMapping
  default void after(PersonData data, @MappingTarget Person target) {
    target.supplierOfMetadata(() -> new MetadataImpl<>(Person.class, data));
  }
}
