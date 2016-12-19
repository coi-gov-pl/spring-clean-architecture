package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity.Entity;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@ToString
@RequiredArgsConstructor
public class Pet implements Entity {
  @Getter
  @NotNull
  @NotBlank
  @Size(max = 20)
  private final String name;
  @Getter
  @NotNull
  private final Race race;
  @Getter
  @Setter
  @Nullable
  private Ownership ownership;
  @Setter
  private List<FormerOwnership> formerOwners = new ArrayList<>();

  public Iterable<FormerOwnership> getFormerOwners() {
    return formerOwners;
  }

  public boolean hasFormerOwners() {
    return !formerOwners.isEmpty();
  }

  public int getFormerOwnerCount() {
    return formerOwners.size();
  }
}
