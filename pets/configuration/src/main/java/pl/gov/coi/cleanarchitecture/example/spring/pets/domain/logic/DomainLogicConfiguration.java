package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@Configuration
class DomainLogicConfiguration {
  @Bean
  ReferenceMapper provideReferenceMapper(@Named("reference-seed") String seed,
                                         Random random) {
    return new AesReferenceMapper(seed, random);
  }

  @Bean
  @Named("reference-seed")
  @ConditionalOnMissingBean
  String provideDefaultReferenceSeed(Random random) {
    RandomString gen = new RandomString(256, random);
    return gen.nextString();
  }

  @Bean
  @ConditionalOnMissingBean
  Random provideDefaultRandom() {
    return ThreadLocalRandom.current();
  }

}
