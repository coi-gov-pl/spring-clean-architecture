package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.usecase.Request;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Ownership;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface RegisterNewPetRequest extends Request {
  String getName();
  String getRace();
  Optional<Ownership> getOwnership();
}
