package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.WithViolations;
import pl.gov.coi.cleanarchitecture.usecase.Response;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public interface UpdatePetResponse extends WithViolations, Response {
}
