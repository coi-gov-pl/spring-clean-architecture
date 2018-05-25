package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider;

import javax.persistence.EntityManager;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Profile("hibernate")
@Configuration
@EnableTransactionManagement
class PersistenceConfiguration {

  @Bean
  public PetsGateway provide(MapperFacade mapper,
                             EntityManager entityManager,
                             QueryProvider queryProvider,
                             PlatformTransactionManager transactionManager) {
    return transactional(
      () -> provideImpl(mapper, entityManager, queryProvider),
      transactionManager
    );
  }

  @Bean
  public PersonGateway providePersonGateway(MapperFacade mapper,
                                            EntityManager entityManager,
                                            QueryProvider queryProvider,
                                            PlatformTransactionManager transactionManager) {
    return transactional(
      () -> providePersonGateway(mapper, entityManager, queryProvider),
      transactionManager
    );
  }

  private PetsGateway provideImpl(MapperFacade mapper,
                                  EntityManager entityManager,
                                  QueryProvider queryProvider) {
    return new HibernatePetsGateway(entityManager, mapper, queryProvider);
  }

  private PersonGateway providePersonGateway(MapperFacade mapper,
                                             EntityManager entityManager,
                                             QueryProvider queryProvider) {
    return new HibernatePersonGateway(entityManager, mapper, queryProvider);
  }

  private <T> T transactional(Supplier<T> targetSupplier,
                              PlatformTransactionManager transactionManager) {
    TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();

    // Inject transaction manager here
    proxy.setTransactionManager(transactionManager);

    // Define which object instance is to be proxied (your bean)
    proxy.setTarget(targetSupplier.get());

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

