package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.editpet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
final class EditPetViewModel {
  private final EditPetForm pet;
  private final Iterable<RaceViewModel> raceViewModel;
  private final Violations violations;
}
