package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.PetMapperFacade;

import javax.persistence.EntityManager;
import java.util.Properties;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Profile("hibernate")
@Configuration
@EnableTransactionManagement
class PersistenceConfiguration {

  private PetsGateway provideImpl(PetMapperFacade mapper,
                                  EntityManager entityManager) {
    return new HibernatePetsGateway(entityManager, mapper);
  }

  @Bean
  public PetsGateway provide(PetMapperFacade mapper,
                             EntityManager entityManager,
                             PlatformTransactionManager transactionManager) {
    TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();

    // Inject transaction manager here
    proxy.setTransactionManager(transactionManager);

    // Define wich object instance is to be proxied (your bean)
    proxy.setTarget(provideImpl(mapper, entityManager));

    // Programmatically setup transaction attributes
    Properties transactionAttributes = new Properties();
    transactionAttributes.put("*", "PROPAGATION_REQUIRED");
    proxy.setTransactionAttributes(transactionAttributes);

    // Finish FactoryBean setup
    proxy.afterPropertiesSet();
    return (PetsGateway) proxy.getObject();

  }
}

