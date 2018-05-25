package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.NotLoaded;
import pl.wavesoftware.utils.mapstruct.jpa.collection.Uninitialized;

import javax.inject.Singleton;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-29
 */
@Configuration
public class MapperConfiguration {

  @Bean
  @Singleton
  public NotLoaded provideNotLoaded() {
    return inspectionPoint -> inspectionPoint
      .getValueSupplier()
      .get() instanceof Uninitialized;
  }
}
