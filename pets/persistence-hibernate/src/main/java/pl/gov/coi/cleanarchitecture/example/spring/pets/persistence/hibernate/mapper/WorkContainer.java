package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import java.io.Closeable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.04.18
 */
public interface WorkContainer<E, D> extends Closeable {
  boolean isContained(E entity);
  D get(E entity);
  void put(E entity, D data);
  @Override
  void close();
}
