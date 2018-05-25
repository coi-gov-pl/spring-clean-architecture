package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
public interface HasMetadata<T> {
  boolean isMetadataSet();
  Metadata<T> getMetadata();
  void supplierOfMetadata(Supplier<Metadata<T>> metadataSupplier);
}
