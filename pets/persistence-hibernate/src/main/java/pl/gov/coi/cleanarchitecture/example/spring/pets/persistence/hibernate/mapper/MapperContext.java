package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@RequiredArgsConstructor
final class MapperContext {
  private final Iterable<MappingContext> mappingContexts;

  @BeforeMapping
  @Nullable
  <T> T getMappedInstance(Object source,
                          @TargetType Class<T> targetType) {
    for (MappingContext mappingContext : mappingContexts) {
      Optional<T> instance = mappingContext.getMappedInstance(source, targetType);
      if (instance.isPresent()) {
        return instance.get();
      }
    }
    return null;
  }

  @BeforeMapping
  void storeMappedInstance(Object source,
                           @MappingTarget Object target) {
    for (MappingContext mappingContext : mappingContexts) {
      if (mappingContext instanceof StoringMappingContext) {
        StoringMappingContext.class.cast(mappingContext)
          .storeMappedInstance(source, target);
      }
    }
  }
}
