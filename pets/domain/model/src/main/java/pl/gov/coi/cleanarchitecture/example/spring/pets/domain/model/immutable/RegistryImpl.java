package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
final class RegistryImpl implements Registry {
  @Override
  public <I, M> void register(Immutablizer<I, M> mutablizer,
                              M mutable,
                              I proxy) {

  }

  @Override
  public <I, M> I get(Immutablizer<I, M> mutablizer, M mutable) {
    return null;
  }
}
