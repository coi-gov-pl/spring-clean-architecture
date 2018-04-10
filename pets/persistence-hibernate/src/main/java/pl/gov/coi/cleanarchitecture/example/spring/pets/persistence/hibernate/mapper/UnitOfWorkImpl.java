package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.04.18
 */
final class UnitOfWorkImpl implements UnitOfWork {

  private final Map<Class<?>, WorkContainer<?, ?>> containers = new HashMap<>();

  @Override
  public <E, D> WorkContainer<E, D> containerOf(Class<E> cls) {
    WorkContainer<E, D> container;
    if (!containers.containsKey(cls)) {
      container = 
    } else {
      container = (WorkContainer<E, D>) containers.get(cls);
    }
    return container;
  }

  @Override
  public void close() {
    containers.clear();
  }
}
