package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import pl.gov.coi.cleanarchitecture.usecase.Response;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface RegisterNewPetResponse extends Response {
  void setViolations(Iterable<RegisterNewPetResponseModel.Violation> violations);
  boolean isSuccessful();
}
