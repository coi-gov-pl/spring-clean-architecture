package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.usecase.Response;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;

import javax.validation.ConstraintViolation;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface RegisterNewPetResponse extends Response {
  void setCreated(Pet pet);
  void setViolations(Iterable<ConstraintViolation<Pet>> violations);
  boolean isSuccessful();
}
