package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wavesoftware.utils.mapstruct.jpa.AbstractJpaContextProvider;
import pl.wavesoftware.utils.mapstruct.jpa.IdentifierCollector;
import pl.wavesoftware.utils.mapstruct.jpa.MappingProvider;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@Service
@RequiredArgsConstructor
final class JpaContextProvider extends AbstractJpaContextProvider {
  private final Provider<EntityManager> entityManagerProvider;
  private final List<MappingProvider<?, ?, ?>> mappingProviders;
  @Getter
  private final IdentifierCollector identifierCollector;

  @Override
  protected Supplier<EntityManager> getEntityManager() {
    return entityManagerProvider::get;
  }

  @Override
  protected Iterable<MappingProvider<?, ?, ?>> getMappingProviders() {
    return mappingProviders;
  }

}
