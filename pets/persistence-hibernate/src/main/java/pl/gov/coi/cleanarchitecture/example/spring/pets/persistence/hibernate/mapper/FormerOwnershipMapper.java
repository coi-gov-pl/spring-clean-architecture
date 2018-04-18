package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.hibernate.Hibernate;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  default List<FormerOwnershipData> map(Iterable<FormerOwnership> formerOwnerships,
                                        @Context CyclicGraphContext context) {
    List<FormerOwnershipData> list = new ArrayList<>();
    for (FormerOwnership formerOwnership : formerOwnerships) {
      list.add(this.map(formerOwnership, context));
    }
    return list;
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