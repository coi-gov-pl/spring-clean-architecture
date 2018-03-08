package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
public interface Registry {
  <I, M> void register(Immutablizer<M, I> immutablizer, M mutable, I proxy);
  <I, M> I get(Immutablizer<M, I> immutablizer, M mutable);
}
