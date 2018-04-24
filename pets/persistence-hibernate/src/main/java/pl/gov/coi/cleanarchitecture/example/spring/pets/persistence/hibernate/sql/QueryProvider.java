package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
public interface QueryProvider {
  OnGoingQueryProviding forClass(Class<?> markerClass);

  interface OnGoingQueryProviding {
    String get(String filename);
  }
}
