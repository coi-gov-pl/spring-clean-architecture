package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(
  componentModel = "jsr330",
  uses = { DateMapper.class, FormerOwnershipMapper.class }
)
public interface OwnershipMapper {
  OwnershipData map(Ownership ownership);
  Ownership map(OwnershipData data);

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  default OwnershipData map(Optional<Ownership> ownership) {
    return ownership.map(this::map)
      .orElse(null);
  }
}
