package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
public interface ClassLocator {
  <T> Class<T> locateClassByName(String className) throws ClassNotFoundException;
}
