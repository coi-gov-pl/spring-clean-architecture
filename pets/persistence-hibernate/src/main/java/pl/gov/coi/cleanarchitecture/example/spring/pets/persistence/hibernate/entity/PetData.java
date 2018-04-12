package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class PetData implements HasRecord {

  private static final long serialVersionUID = 20180307225158L;

  private Record record = new Record();
  private String name;
  private Race race;
  private OwnershipData ownership;
  private List<FormerOwnershipData> formerOwners = new ArrayList<>();

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

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
