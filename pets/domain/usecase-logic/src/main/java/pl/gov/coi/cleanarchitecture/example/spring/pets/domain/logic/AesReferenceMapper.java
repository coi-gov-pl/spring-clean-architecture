package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.util.Optional;
import java.util.Random;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
final class AesReferenceMapper implements ReferenceMapper {
  private final String seed;
  private final Random random;

  AesReferenceMapper(String seed, Random random) {
    this.seed = seed;
    this.random = random;

  }

  @Override
  public <T extends HasMetadata> Representation<T> map(HasMetadata<T> hasMetadata) {
    Metadata<T> metadata = hasMetadata.getMetadata();
    @SuppressWarnings("unchecked")
    Class<T> type = (Class<T>) hasMetadata.getClass();
    Optional<Object> ref = metadata
      .get(Reference.class)
      .map(Reference::get);
    return Representation.repr(ref.orElse("none").toString(), type);
  }

  @Override
  public <T extends HasMetadata> T map(Representation<T> representation) {
    throw new UnsupportedOperationException("not yet implemented");
  }

}
