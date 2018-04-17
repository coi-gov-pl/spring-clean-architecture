package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@NoArgsConstructor
public class PetData extends Record {

  private static final long serialVersionUID = 20180307225158L;

  private String name;
  private Race race;
  private OwnershipData ownership;
  private List<FormerOwnershipData> formerOwners = new ArrayList<>();

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
  public List<FormerOwnershipData> getFormerOwners() {
    return formerOwners;
  }

}
