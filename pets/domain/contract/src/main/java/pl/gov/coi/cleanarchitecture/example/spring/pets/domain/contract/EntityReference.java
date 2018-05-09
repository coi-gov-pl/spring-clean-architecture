package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
public interface EntityReference {
  Serializable getType();
  @Nullable
  Serializable getReference();
}
