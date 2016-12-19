package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.presenter;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.example.spring.cleanarchitecture.core.presentation.Presenter;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Race;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.registernewpet.RegisterNewPetResponse;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.NewPetView;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.NewPetDTO;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.RaceDTO;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor(staticName = "create")
public class NewPetPresenter implements Presenter<NewPetView>, RegisterNewPetResponse {
  @Nullable
  private Pet created;
  @Nullable
  private Iterable<ConstraintViolation<Pet>> violations;

  @Override
  public NewPetView createView() {
    return NewPetView.create()
      .setViewModel(createModel());
  }

  private NewPetDTO createModel() {
    return new NewPetDTO(
      Arrays.stream(Race.values())
        .map(this::toDto)
        .collect(Collectors.toList()),
      created,
      violations
    );
  }

  private RaceDTO toDto(Race race) {
    return new RaceDTO(race.name(), race.getName());
  }

  @Override
  public void setCreated(Pet pet) {
    this.created = pet;
  }

  @Override
  public void setViolations(Iterable<ConstraintViolation<Pet>> violations) {
    this.violations = violations;
  }

  @Override
  public boolean isSuccessful() {
    return created != null;
  }
}
