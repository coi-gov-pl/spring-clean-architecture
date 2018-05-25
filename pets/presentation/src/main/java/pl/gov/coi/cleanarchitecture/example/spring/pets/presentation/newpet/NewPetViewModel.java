package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.model.RaceViewModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation.Violations;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
class NewPetViewModel {
  private final Iterable<RaceViewModel> raceViewModel;
  private final Violations violations;
}
