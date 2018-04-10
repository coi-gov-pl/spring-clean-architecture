package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.04.18
 */
public interface Converter<E, D> extends AutoCloseable {
  D convert(E entity);
}
