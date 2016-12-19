package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public enum Race {
  CAT, DOG;

  public String getName() {
    return name().toLowerCase();
  }
}
