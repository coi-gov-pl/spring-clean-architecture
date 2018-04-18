package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-07
 */
@Data
@ToString(of = "from")
@AllArgsConstructor
public final class Ownership implements Serializable {

  private static final long serialVersionUID = 20180412143844L;

  private Pet pet;
  private Person person;
  private Instant from;

  /**
   * Default constructor
   */
  public Ownership() {
    from = Instant.now();
  }
}
