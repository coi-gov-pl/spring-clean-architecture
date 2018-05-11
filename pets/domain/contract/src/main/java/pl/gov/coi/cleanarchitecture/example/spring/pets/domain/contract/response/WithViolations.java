package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public interface WithViolations extends IsSuccessful {
  void setViolations(Iterable<Violation> violations);
}
