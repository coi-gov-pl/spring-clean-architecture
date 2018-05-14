package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.form.PetForm;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
final class EditPetForm {
  private String reference;
  private PetForm data;
}
