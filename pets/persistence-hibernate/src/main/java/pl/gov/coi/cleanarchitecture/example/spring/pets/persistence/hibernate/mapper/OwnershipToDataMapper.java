package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-20
 */
public interface OwnershipToDataMapper {
  OwnershipData map(Ownershssip ownership);
  Ownershssip map(OwnershipData ownership);
}
