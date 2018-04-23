package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.hibernate.Hibernate;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;

import java.util.List;
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
    OwnershipMapper.class
  }
)
public interface FormerOwnershipMapper {
  FormerOwnershipData map(FormerOwnership formerOwnership,
                          @Context CyclicGraphContext context);
  FormerOwnership map(FormerOwnershipData data,
                      @Context CyclicGraphContext context);

  @AfterMapping
  default void after(FormerOwnershipData data, @MappingTarget FormerOwnership target) {
    target.supplierOfMetadata(() -> new MetadataImpl<>(FormerOwnership.class, data));
  }

  default List<FormerOwnershipData> map(Iterable<FormerOwnership> formerOwnerships,
                                        @Context CyclicGraphContext context) {
    return StreamSupport
      .stream(formerOwnerships.spliterator(), false)
      .map(formerOwnership -> map(formerOwnership, context))
      .collect(Collectors.toList());
  }

  default List<FormerOwnership> map(List<FormerOwnershipData> formerOwnershipData,
                                    @Context CyclicGraphContext context) {
    if (!Hibernate.isInitialized(formerOwnershipData)) {
      return new UninitializedList<>(FormerOwnershipData.class);
    }
    return formerOwnershipData
      .stream()
      .map(data -> map(data, context))
      .collect(Collectors.toList());
  }
}
