package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.04.18
 */
public interface UnitOfWork extends AutoCloseable {

  <E, D> WorkContainer<E, D> containerOf(Class<E> cls);

  @Override
  void close();
}
