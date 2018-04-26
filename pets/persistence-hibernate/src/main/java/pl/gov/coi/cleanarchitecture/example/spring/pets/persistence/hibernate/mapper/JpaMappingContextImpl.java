package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.AbstractEntity;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@RequiredArgsConstructor
final class JpaMappingContextImpl implements JpaMappingContext {
  private final EntityManager entityManager;
  private final StoringMappingContext parentContext;
  private final Mappings mappings;
  private final Set<Integer> mappedInstances = new HashSet<>();

  @Override
  public <T> Optional<T> getMappedInstance(Object source,
                                           Class<T> targetType) {
    if (targetType.isAnnotationPresent(Entity.class)) {
      Optional<Reference> reference = getReference(source);
      if (reference.isPresent()) {
        if (isBeingMapped(source, targetType)) {
          return Optional.empty();
        }
        markAsBeingMapped(source, targetType);
      }
      Optional<T> managed = reference.map(ref -> load(ref, targetType));
      managed.ifPresent(m -> updateFromSource(m, source));
      return managed;
    }
    return Optional.empty();
  }

  private <T> void markAsBeingMapped(Object source,
                                     Class<T> targetType) {
    Integer key = keyOf(source, targetType);
    mappedInstances.add(key);
  }

  private <T> boolean isBeingMapped(Object source,
                                    Class<T> targetType) {
    Integer key = keyOf(source, targetType);
    return mappedInstances.contains(key);
  }

  private <T> Integer keyOf(Object source, Class<T> targetType) {
    return System.identityHashCode(source) +
      System.identityHashCode(targetType);
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

  private <T> void updateFromSource(T managed,
                                    Object source) {
    updateFromSourceTyped(source, managed);
  }

  @SuppressWarnings("unchecked")
  private <I, O, C> void updateFromSourceTyped(I source, O managed) {
    Class<I> sourceClass = (Class<I>) source.getClass();
    Class<O> targetClass = (Class<O>) managed.getClass();
    C context = (C) parentContext;
    Mapping<I, O, C> mapping = mappings.getMapping(sourceClass, targetClass);
    mapping.accept(source, managed, context);
  }
}
