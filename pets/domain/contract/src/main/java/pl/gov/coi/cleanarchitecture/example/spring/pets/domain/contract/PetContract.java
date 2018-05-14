package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.Capitalized;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.IsSpecified;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.Specified;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@Getter
@Builder
@ToString
public final class PetContract {

  @NotNull
  @NotBlank
  @Size(max = 20)
  @Capitalized
  private final String name;
  @Specified
  private final Race race;
  @Nullable
  @Valid
  private final Ownership ownership;

  public PetContract(String name,
                     Race race,
                     @Nullable Ownership ownership) {
    this.name = name;
    this.race = race;
    this.ownership = ownership;
  }

  @RequiredArgsConstructor
  @Getter
  @ToString
  public static final class Ownership {
    @NotBlank
    @Capitalized
    private final String name;
    @NotBlank
    @Capitalized
    private final String surname;
  }

  public enum Race implements IsSpecified {

    CAT, DOG, GUINEA_PIG, UNSPECIFIED(false);

    private final boolean isSpecified;

    Race() {
      this(true);
    }
    Race(boolean isSpecified) {
      this.isSpecified = isSpecified;
    }

    @Override
    public boolean isSpecified() {
      return isSpecified;
    }
  }
}
