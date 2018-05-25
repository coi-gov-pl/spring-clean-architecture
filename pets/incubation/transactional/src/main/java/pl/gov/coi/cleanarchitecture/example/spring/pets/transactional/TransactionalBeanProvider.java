package pl.gov.coi.cleanarchitecture.example.spring.pets.transactional;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.05.18
 */
public interface TransactionalBeanProvider {
  <T> T transactional(Supplier<T> beanSupplier);
}
