package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.hibernate.Hibernate;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = {
    PetMapper.class,
    DateMapper.class,
    PersonMapper.class,
    FormerOwnershipMapper.class
  }
)
public interface OwnershipMapper {
  OwnershipData map(Ownership ownership, @Context MapperContext context);
  Ownership map(OwnershipData data, @Context MapperContext context);
  void updateFromOwnership(Ownership ownership,
                           @MappingTarget OwnershipData data,
                           @Context MapperContext context);

  @AfterMapping
  default void after(OwnershipData data, @MappingTarget Ownership target) {
    target.supplierOfMetadata(() -> new MetadataImpl<>(Ownership.class, data));
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  default OwnershipData map(Optional<Ownership> optional,
                            @Context MapperContext context) {
    return optional.map(ownership -> this.map(ownership, context))
      .orElse(null);
  }

  default Set<OwnershipData> map(Iterable<Ownership> ownerships,
                                 @Context MapperContext context) {
    return StreamSupport
      .stream(ownerships.spliterator(), false)
      .map(ownership -> map(ownership, context))
      .collect(Collectors.toSet());
  }

  default List<Ownership> map(Set<OwnershipData> ownershipDataList,
                              @Context MapperContext context) {
    if (!Hibernate.isInitialized(ownershipDataList)) {
      return new UninitializedList<>(OwnershipData.class);
    }
    return ownershipDataList
      .stream()
      .map(data -> map(data, context))
      .collect(Collectors.toList());
  }
}
