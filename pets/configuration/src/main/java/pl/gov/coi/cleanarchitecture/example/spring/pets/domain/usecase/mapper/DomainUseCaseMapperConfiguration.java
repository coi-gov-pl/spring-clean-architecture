package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-14
 */
@Configuration
class DomainUseCaseMapperConfiguration {
  @Bean
  EnumMapper<PetContract.Race, Race> provideRaceMapper() {
    return new RaceEnumMapper();
  }

  @Bean
  PetContractMapper providePetContractMapper(EnumMapper<PetContract.Race, Race> raceMapper) {
    return new PetContractMapperImpl(raceMapper);
  }
}
