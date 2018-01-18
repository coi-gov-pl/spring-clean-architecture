package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Table
public class PetData extends AbstractEntity {
  @Size(min = 2, max = 10)
  private String name;
  @NotNull
  private Race race;
  @Valid
  @Nullable
  private OwnershipData ownership;
  private List<FormerOwnershipData> formerOwnerships;

  @Column
  public String getName() {
    return name;
  }

  @Enumerated
  public Race getRace() {
    return race;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public OwnershipData getOwnership() {
    return ownership;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public List<FormerOwnershipData> getFormerOwnerships() {
    return new ArrayList<>(formerOwnerships);
  }

  public void setFormerOwnerships(List<FormerOwnershipData> formerOwnerships) {
    this.formerOwnerships = new ArrayList<>(formerOwnerships);
  }
}
