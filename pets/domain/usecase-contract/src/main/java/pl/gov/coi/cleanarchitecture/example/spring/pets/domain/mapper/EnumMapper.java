package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public interface EnumMapper<I extends Enum, O extends Enum> {
  O map(I input);
  I reverseMap(O output);
  Class<I> getInputEnumClass();
  Class<O> getOutputEnumClass();
}
