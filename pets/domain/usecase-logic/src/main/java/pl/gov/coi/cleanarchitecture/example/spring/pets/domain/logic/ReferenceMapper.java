package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
public interface ReferenceMapper {
  <T extends HasMetadata> Representation<T> map(HasMetadata<T> hasMetadata);
  <T extends HasMetadata> T map(Representation<T> representation);

  interface Representation<T extends HasMetadata> {
    static <T extends HasMetadata> Representation<T> repr(CharSequence repr,
                                                          Class<T> cls) {
      return new CharSequenceRepresentation<>(repr, cls);
    }
    CharSequence getCharSequence();
    Class<T> getType();
    @Override
    String toString();
  }

  class CharSequenceRepresentation<T extends HasMetadata> implements Representation<T> {
    private final CharSequence repr;
    private final Class<T> cls;
    CharSequenceRepresentation(CharSequence repr, Class<T> cls) {
      this.repr = repr;
      this.cls = cls;
    }

    @Override
    public CharSequence getCharSequence() {
      return repr;
    }

    @Override
    public Class<T> getType() {
      return cls;
    }

    @Override
    public String toString() {
      return repr.toString();
    }
  }
}
