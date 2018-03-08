package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@javax.persistence.Entity
@Table
@Setter
public class PersonData extends Record {
  @NotNull
  private String name;
  @NotNull
  private String surname;
  private List<OwnershipData> ownerships = new ArrayList<>();

  @Column
  public String getName() {
    return name;
  }

  @Column
  public String getSurname() {
    return surname;
  }

  @OneToMany
  public List<OwnershipData> getOwnerships() {
    return new ArrayList<>(ownerships);
  }

  public void setOwnership(List<OwnershipData> ownerships) {
    this.ownerships = new ArrayList<>(ownerships);
  }
}
