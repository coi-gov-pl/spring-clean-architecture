package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
final class MapperContext implements StoringMappingContext {
  private final List<MappingContext> mappingContexts = new ArrayList<>();

  MapperContext(MappingContext... mappingContexts) {
    Collections.addAll(this.mappingContexts, mappingContexts);
  }

  void addContext(MappingContext context) {
    mappingContexts.add(context);
  }

  @BeforeMapping
  <T> T getMappedInstanceForMapstruct(Object source,
                                      @TargetType Class<T> targetType) {
    return getMappedInstance(source, targetType)
      .orElse(null);
  }

  public <T> Optional<T> getMappedInstance(Object source,
                                           Class<T> targetType) {
    for (MappingContext mappingContext : mappingContexts) {
      Optional<T> instance = mappingContext.getMappedInstance(source, targetType);
      if (instance.isPresent()) {
        return instance;
      }
    }
    return Optional.empty();
  }

  @BeforeMapping
  public void storeMappedInstance(Object source,
                                  @MappingTarget Object target) {
    for (MappingContext mappingContext : mappingContexts) {
      if (mappingContext instanceof StoringMappingContext) {
        StoringMappingContext.class.cast(mappingContext)
          .storeMappedInstance(source, target);
      }
    }
  }
}
