package pl.gov.coi.cleanarchitecture.example.spring.pets.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.05.18
 */
@Service
@RequiredArgsConstructor
final class SpringTransactionalBeanProvider implements TransactionalBeanProvider {

  private final PlatformTransactionManager transactionManager;

  @Override
  public <T> T transactional(Supplier<T> beanSupplier) {
    TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();

    // Inject transaction manager here
    proxy.setTransactionManager(transactionManager);

    // Define which object instance is to be proxied (your bean)
    proxy.setTarget(beanSupplier.get());

    // Programmatically setup transaction attributes
    Properties transactionAttributes = new Properties();
    transactionAttributes.put("*", "PROPAGATION_REQUIRED");
    proxy.setTransactionAttributes(transactionAttributes);

    // Finish FactoryBean setup
    proxy.afterPropertiesSet();
    @SuppressWarnings("unchecked")
    T ret = (T) proxy.getObject();
    return ret;
  }
}
