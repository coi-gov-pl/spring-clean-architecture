package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolation;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
@Getter
public class NewPetDTO {
  private final Iterable<RaceDTO> raceDTOs;
  @Nullable
  private final Pet created;
  @Nullable
  private final Iterable<ConstraintViolation<Pet>> violations;
}
