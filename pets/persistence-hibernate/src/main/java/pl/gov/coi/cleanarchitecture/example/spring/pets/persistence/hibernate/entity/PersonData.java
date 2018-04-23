package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wavesoftware.utils.stringify.annotation.Inspect;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
public class PersonData extends Record {

  @Inspect
  private String name;
  @Inspect
  private String surname;
  private List<OwnershipData> ownerships = new ArrayList<>();

  // JPA Mappings

  @NotNull
  @Column
  public String getName() {
    return name;
  }

  @NotNull
  @Column
  public String getSurname() {
    return surname;
  }

  @OneToMany(cascade = CascadeType.ALL)
  public List<OwnershipData> getOwnerships() {
    return ownerships;
  }

}
