package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.wavesoftware.utils.mapstruct.jpa.IdentifierCollector;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@Service
final class MetadataIdentifierCollector implements IdentifierCollector {

  @Override
  public Optional<Object> getIdentifierFromSource(Object source) {
    if (source instanceof HasMetadata) {
      HasMetadata<?> hasMetadata = HasMetadata.class.cast(source);
      Optional<Reference> ref = hasMetadata
        .getMetadata()
        .get(Reference.class);
      return ref.map(Reference::get);
    }
    return Optional.empty();
  }

}
