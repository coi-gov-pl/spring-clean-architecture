package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.Context;
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
  uses = {
    PetMapper.class,
    DateMapper.class,
    PersonMapper.class,
    FormerOwnershipMapper.class
  }
)
public interface OwnershipMapper {
  OwnershipData map(Ownership ownership, @Context CyclicGraphContext context);
  Ownership map(OwnershipData data, @Context CyclicGraphContext context);

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  default OwnershipData map(Optional<Ownership> optional,
                            @Context CyclicGraphContext context) {
    return optional.map(ownership -> this.map(ownership, context))
      .orElse(null);
  }
}
