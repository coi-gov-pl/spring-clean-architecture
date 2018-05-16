package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Created;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.MetadataEntry;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Modified;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Supplier;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkState;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
final class SetStubDatabase implements StubDatabase {
  private final SortedSet<Pet> pets = new TreeSet<>(
    new MetadataComparator()
  );
  private long sequence = 1L;

  @Override
  public Iterable<Pet> getPets() {
    return pets;
  }

  @Override
  public long getNumberOfPets() {
    return pets.size();
  }

  @Override
  public void putOrUpdate(Pet pet) {
    Optional<Pet> managed = find(pet);
    if (managed.isPresent()) {
      update(managed.get(), pet);
    } else {
      pet.supplierOfMetadata(provideSupplier());
      pets.add(pet);
    }
  }

  private void update(Pet managed, Pet toBeReplaced) {
    checkState(
      pets.remove(managed),
      "20180515:161804"
    );
    StubMetadata<Pet> meta = (StubMetadata<Pet>) managed.getMetadata();
    StubMetadata<Pet> updated = meta.update();
    toBeReplaced.supplierOfMetadata(() -> updated);
    pets.add(toBeReplaced);
  }

  private Optional<Pet> find(HasMetadata<Pet> hasMetadata) {
    Metadata<Pet> meta = hasMetadata.getMetadata();
    Optional<Reference> ref = meta
      .get(Reference.class);
    if (ref.isPresent()) {
      for (Pet managed : pets) {
        Optional<Reference> managedRef = managed
          .getMetadata()
          .get(Reference.class);
        if (managedRef.isPresent() && ref.get().isEqualTo(managedRef.get())) {
          return Optional.of(managed);
        }
      }
    }
    return Optional.empty();
  }

  private Supplier<Metadata<Pet>> provideSupplier() {
    return new MetadataSupplier<>(Pet.class, nextId());
  }

  private synchronized long nextId() {
    return sequence++;
  }

  private static final class MetadataSupplier<T> implements Supplier<Metadata<T>> {

    private final Metadata<T> meta;

    private MetadataSupplier(Class<T> type, long id) {
      this.meta = new StubMetadata<>(type, id);
    }

    @Override
    public Metadata<T> get() {
      return meta;
    }
  }

  private static final class StubMetadata<T> implements Metadata<T> {

    private final Class<T> type;
    private final long id;
    private final Instant created;
    private final Instant modified;

    private StubMetadata(Class<T> type,
                         long id) {
      this.type = type;
      this.id = id;
      this.created = Instant.now();
      this.modified = Instant.now();
    }

    private StubMetadata(StubMetadata<T> metadata) {
      this.type = metadata.type;
      this.id = metadata.id;
      this.created = metadata.created;
      this.modified = Instant.now();
    }

    private StubMetadata<T> update() {
      return new StubMetadata<>(this);
    }

    @Override
    public <I, D extends MetadataEntry<I>> Optional<D> get(Class<D> metadataClass) {
      if (metadataClass == Reference.class) {
        return Optional.of(
          metadataClass.cast((Reference) () -> id)
        );
      }
      if (metadataClass == Created.class) {
        return Optional.of(
          metadataClass.cast((Created) () -> created)
        );
      }
      if (metadataClass == Modified.class) {
        return Optional.of(
          metadataClass.cast((Modified) () -> modified)
        );
      }
      return Optional.empty();
    }

    @Override
    public Class<T> type() {
      return type;
    }
  }

  private class MetadataComparator implements Comparator<HasMetadata<?>> {
    @Override
    public int compare(HasMetadata<?> left, HasMetadata<?> right) {
      Optional<Reference> leftRef = left.getMetadata().get(Reference.class);
      Optional<Reference> rightRef = right.getMetadata().get(Reference.class);
      if (leftRef.isPresent() && rightRef.isPresent()) {
        Long leftId = Long.class.cast(leftRef.get().get());
        Long rightId = Long.class.cast(rightRef.get().get());
        return (int) (leftId - rightId);
      }
      return 0;
    }
  }
}
