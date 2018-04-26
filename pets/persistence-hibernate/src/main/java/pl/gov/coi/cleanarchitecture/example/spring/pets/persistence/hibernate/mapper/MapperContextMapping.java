package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
abstract class MapperContextMapping<I, O> extends Mapping<I, O, MapperContext> {

  MapperContextMapping(Class<I> sourceClass, Class<O> targetClass) {
    super(sourceClass, targetClass, MapperContext.class);
  }

  static <I, O> MapperContextMapping<I, O> mapperFor(Class<I> inputClass,
                                                     Class<O> outputClass,
                                                     TriConsumer<I, O, MapperContext> consumer) {
    return new MapperContextMapping<I, O>(inputClass, outputClass) {
      @Override
      public void accept(I input, O output, MapperContext context) {
        consumer.accept(input, output, context);
      }
    };
  }
}
