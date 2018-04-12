package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * A type to be used as {@link Context} parameter to track cycles in graphs.
 * <p>
 * Depending on the actual use case, the two methods below could also be changed to only accept certain argument types,
 * e.g. base classes of graph nodes, avoiding the need to capture any other objects that wouldn't necessarily result in
 * cycles.
 *
 * @author Andreas Gudian
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-12
 */
public final class CyclicGraphContext {
  private Map<Object, Object> knownInstances = new IdentityHashMap<>();

  @BeforeMapping
  <T> T getMappedInstance(Object source,
                          @TargetType Class<T> targetType) {
    return targetType.cast(knownInstances.get( source ));
  }

  @BeforeMapping
  void storeMappedInstance(Object source,
                           @MappingTarget Object target) {
    knownInstances.put( source, target );
  }
}
