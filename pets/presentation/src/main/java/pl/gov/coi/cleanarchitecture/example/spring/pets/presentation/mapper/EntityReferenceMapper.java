package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
public interface EntityReferenceMapper {
  CharSequence map(EntityReference reference);
  Optional<EntityReference> map(CharSequence repr);

  enum Constants {
    ;
    public static final String KEY = "reference-mapper-key";
    public static final String TRY_UNLIMITED = "reference-mapper-try-unlimited";
  }
}
