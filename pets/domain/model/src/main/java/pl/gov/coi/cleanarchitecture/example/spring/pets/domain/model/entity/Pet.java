package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@ToString
@Builder
public class Pet implements Serializable {
  private static final long serialVersionUID = 20180308212350L;

  @Getter
  private final String name;
  @Getter
  private final Race race;
  @Nullable
  private final Ownership ownership;
  private final List<FormerOwnership> formerOwners;

  /**
   * Default constructor
   *
   * @param name         a name of a pet
   * @param race         a race of a pet
   * @param ownership    an ownership of a pet, can be empty
   * @param formerOwners a list of former owners of this pet
   */
  public Pet(String name, Race race,
             @Nullable Ownership ownership,
             List<FormerOwnership> formerOwners) {
    this.name = Objects.requireNonNull(name, "20180308:214932");
    this.race = Objects.requireNonNull(race, "20180308:214944");
    this.ownership = ownership;
    this.formerOwners = Collections.unmodifiableList(formerOwners);
  }

  /**
   * Easy constructor
   * @param name a name
   * @param race a race
   */
  public Pet(String name, Race race) {
    this(name, race, null, new ArrayList<>());
  }

  public Iterable<FormerOwnership> getFormerOwners() {
    return Collections.unmodifiableList(formerOwners);
  }

  /**
   * Returns true if pet have any former owners
   *
   * @return true if pet have any former owners
   */
  public boolean hasFormerOwners() {
    return !formerOwners.isEmpty();
  }

  /**
   * Get number of former owners
   * @return number of former owners
   */
  public int getFormerOwnerCount() {
    return formerOwners.size();
  }

  public Optional<Ownership> getOwnership() {
    return Optional.ofNullable(ownership);
  }
}
