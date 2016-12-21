package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponseModel;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
class NewPetViewModel {
  private final Iterable<RaceViewModel> raceViewModel;
  @Nullable
  private final Iterable<RegisterNewPetResponseModel.Violation> violations;
}
