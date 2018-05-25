package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
interface ClassNameEncoder {
  String getReprForClassName(String className);
  String getClassNameFromRepr(String repr);
}
