package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity.Entity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
@RequiredArgsConstructor
public class Person implements Entity {
  @Getter
  @NotNull
  private final String name;
  @Getter
  @NotNull
  private final String surname;
  private List<Ownership> ownerships = new ArrayList<>();

  public void addOwnership(Ownership ownership) {
    this.ownerships.add(ownership);
  }

  public int getOwnershipCount() {
    return ownerships.size();
  }

  public boolean hasOwnerships() {
    return !ownerships.isEmpty();
  }

  public Iterable<Ownership> getOwnerships() {
    return ownerships;
  }
}
