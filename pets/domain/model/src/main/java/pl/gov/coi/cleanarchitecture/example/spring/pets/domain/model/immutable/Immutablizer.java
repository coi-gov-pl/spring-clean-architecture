package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
public interface Immutablizer<M, I> {
  I immutable(M mutable);

  static Registry registry() {
    return new RegistryImpl();
  }
}
