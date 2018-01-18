package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
  @NotNull
  private Long id;
  @Setter(AccessLevel.PROTECTED)
  private Long recordVersion;
  @NotNull
  private Date created;
  @NotNull
  private Date modified;

  @GenericGenerator(
    name = "idSequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
  )
  @Id
  @GeneratedValue(generator = "idSequenceGenerator")
  public Long getId() {
    return id;
  }

  @PrePersist
  protected void beforePersistAbstractEntity() {
    Date now = new Date();
    if (created == null) {
      created = now;
    }
    modified = now;
  }

  @PreUpdate
  protected void beforeUpdateAbstractEntity() {
    modified = new Date();
  }

  @Version
  public Long getRecordVersion() {
    return recordVersion;
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  public Date getCreated() {
    return created;
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  public Date getModified() {
    return modified;
  }
}
