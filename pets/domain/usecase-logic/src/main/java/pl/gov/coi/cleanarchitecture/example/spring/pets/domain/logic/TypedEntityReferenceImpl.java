package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;

import java.io.Serializable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
@Getter
@RequiredArgsConstructor
final class TypedEntityReferenceImpl<T extends HasMetadata<T>>
  implements TypedEntityReference<T> {
  private final Class<T> type;
  private final Serializable reference;
}
