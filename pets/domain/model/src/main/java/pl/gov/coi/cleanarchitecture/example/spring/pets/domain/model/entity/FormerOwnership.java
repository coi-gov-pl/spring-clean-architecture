package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface FormerOwnership extends Ownership {
  Instant getTo();
  void setTo(Instant to);
}
