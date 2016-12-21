package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Pet implements Entity {
  @Getter
  private final String name;
  @Getter
  private final Race race;
  @Getter
  @Setter
  @Nullable
  private Ownership ownership;
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

  public void setFormerOwners(Iterable<FormerOwnership> formerOwners) {
    this.formerOwners = StreamSupport
      .stream(formerOwners.spliterator(), false)
      .collect(Collectors.toList());
  }
}
