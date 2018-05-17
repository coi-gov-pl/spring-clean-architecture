package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Entity
@Table
@Getter
@Setter
@Access(AccessType.PROPERTY)
@NoArgsConstructor
public class PetData extends Record {

  private static final long serialVersionUID = 20180307225158L;

  private String name;
  private Race race;
  private OwnershipData ownership;
  private Set<FormerOwnershipData> formerOwners = new HashSet<>();

  @Column
  public String getName() {
    return name;
  }

  @Enumerated(EnumType.STRING)
  public Race getRace() {
    return race;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public OwnershipData getOwnership() {
    return ownership;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public Set<FormerOwnershipData> getFormerOwners() {
    return formerOwners;
  }

}
