package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.AbstractEntity;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@Service
@RequiredArgsConstructor
final class JpaMappingContextImpl implements JpaMappingContext {
  private final EntityManager entityManager;

  @Override
  public <T> Optional<T> getMappedInstance(Object source,
                                           Class<T> targetType) {
    if (targetType.isAnnotationPresent(Entity.class)) {
      Optional<Reference> reference = getReference(source);
      return reference.map(ref -> load(ref, targetType));
    }
    return Optional.empty();
  }

  private <T> T load(Reference<?, ?> reference, Class<T> targetType) {
    return entityManager.find(targetType, reference.get());
  }

  private Optional<Reference> getReference(Object source) {
    if (source instanceof AbstractEntity) {
      AbstractEntity<?> entity = AbstractEntity.class.cast(source);
      return entity
        .getMetadata()
        .get(Reference.class);
    }
    return Optional.empty();
  }
}
