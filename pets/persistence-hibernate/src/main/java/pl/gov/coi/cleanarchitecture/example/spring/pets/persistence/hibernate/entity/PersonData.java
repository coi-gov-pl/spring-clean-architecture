package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@Access(AccessType.PROPERTY)
@NoArgsConstructor
public class PersonData extends Record {

  private String name;
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

  @OneToMany
  public List<OwnershipData> getOwnerships() {
    return ownerships;
  }

}
