package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
public interface Reference<I, T> extends MetadataEntry<I, T> {
  @Override
  I get();
}
