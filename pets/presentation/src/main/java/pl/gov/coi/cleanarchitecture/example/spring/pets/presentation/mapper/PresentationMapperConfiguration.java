package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper.EntityReferenceMapper.Constants;
import pl.gov.coi.cleanarchitecture.example.spring.pets.random.RandomString;

import javax.inject.Named;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
@Configuration
class PresentationMapperConfiguration {
  @Bean
  @Named(Constants.TRY_UNLIMITED)
  @ConditionalOnMissingBean
  boolean provideReferenceMapperTryUnlimited() {
    return true;
  }

  @Bean
  @Named(Constants.KEY)
  @ConditionalOnMissingBean
  CharSequence provideReferenceMapperKey(Random random) {
    RandomString rnd = new RandomString(512, random);
    return rnd.nextString();
  }

  @Bean
  @ConditionalOnMissingBean
  Random provideRandom() {
    return ThreadLocalRandom.current();
  }

}
