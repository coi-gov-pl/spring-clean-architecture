package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-07
 */
@Getter
@Setter
@AllArgsConstructor
public class Ownership extends AbstractEntity<Ownership> implements Serializable {

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
