package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.io.Serializable;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
final class EntityReferenceMapperImpl implements EntityReferenceMapper {

  @Override
  public <T extends HasMetadata<T>> TypedEntityReference<T> map(T hasMetadata) {
    @SuppressWarnings("unchecked")
    Class<T> type = (Class<T>) hasMetadata.getClass();
    Metadata<T> meta = hasMetadata.getMetadata();
    Serializable ref = meta.get(Reference.class)
      .map(Reference::get)
      .orElse(null);
    return new TypedEntityReferenceImpl<>(type, ref);
  }

}
