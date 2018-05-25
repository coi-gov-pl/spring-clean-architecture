package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalArgumentException;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
class RaceEnumMapper implements EnumMapper<PetContract.Race, Race> {
  @Override
  public Race map(PetContract.Race input) {
    switch (input) {
      case CAT:
        return Race.CAT;
      case DOG:
        return Race.DOG;
      case GUINEA_PIG:
        return Race.PIG;
      default:
        throw new EidIllegalArgumentException(new Eid("20161221:183136"));
    }
  }

  @Override
  public PetContract.Race reverseMap(Race output) {
    switch (output) {
      case CAT:
        return PetContract.Race.CAT;
      case DOG:
        return PetContract.Race.DOG;
      case PIG:
        return PetContract.Race.GUINEA_PIG;
      default:
        throw new EidIllegalArgumentException(new Eid("20161221:191805"));
    }
  }

  @Override
  public Class<PetContract.Race> getInputEnumClass() {
    return PetContract.Race.class;
  }

  @Override
  public Class<Race> getOutputEnumClass() {
    return Race.class;
  }

}
