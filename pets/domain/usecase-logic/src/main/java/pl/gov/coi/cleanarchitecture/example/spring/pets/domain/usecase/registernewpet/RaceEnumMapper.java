package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalArgumentException;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
@Component
class RaceEnumMapper implements EnumMapper<RegisterNewPetRequestModel.Race, Race> {
  @Override
  public Race map(RegisterNewPetRequestModel.Race input) {
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
  public RegisterNewPetRequestModel.Race reverseMap(Race output) {
    switch (output) {
      case CAT:
        return RegisterNewPetRequestModel.Race.CAT;
      case DOG:
        return RegisterNewPetRequestModel.Race.DOG;
      case PIG:
        return RegisterNewPetRequestModel.Race.GUINEA_PIG;
      default:
        throw new EidIllegalArgumentException(new Eid("20161221:191805"));
    }
  }

  @Override
  public Class<RegisterNewPetRequestModel.Race> getInputEnumClass() {
    return RegisterNewPetRequestModel.Race.class;
  }

  @Override
  public Class<Race> getOutputEnumClass() {
    return Race.class;
  }

}
