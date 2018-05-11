package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form.PetForm;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Setter
@Getter
@RequiredArgsConstructor
final class EditPetForm {
  private final String reference;
  private final PetForm data;
}
