package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = { DateMapper.class, OwnershipMapper.class }
)
public interface FormerOwnershipMapper {
  FormerOwnershipData map(FormerOwnership formerOwnership);
  FormerOwnership map(FormerOwnershipData data);

  default List<FormerOwnershipData> map(Iterable<FormerOwnership> formerOwnerships) {
    List<FormerOwnershipData> list = new ArrayList<>();
    for (FormerOwnership formerOwnership : formerOwnerships) {
      list.add(this.map(formerOwnership));
    }
    return list;
  }
}
