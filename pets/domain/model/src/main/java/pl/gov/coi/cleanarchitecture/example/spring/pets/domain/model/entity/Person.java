package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Person implements Entity {
  @Getter
  private final String name;
  @Getter
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
