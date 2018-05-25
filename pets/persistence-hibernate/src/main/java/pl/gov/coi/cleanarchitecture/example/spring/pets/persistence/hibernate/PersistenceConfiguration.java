package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider;
import pl.gov.coi.cleanarchitecture.example.spring.pets.transactional.TransactionalBeanProvider;

import javax.persistence.EntityManager;

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
                             TransactionalBeanProvider transactionalBeanProvider) {
    return transactionalBeanProvider.transactional(
      () -> new HibernatePetsGateway(entityManager, mapper, queryProvider)
    );
  }

  @Bean
  public PersonGateway providePersonGateway(MapperFacade mapper,
                                            EntityManager entityManager,
                                            QueryProvider queryProvider,
                                            TransactionalBeanProvider transactionalBeanProvider) {
    return transactionalBeanProvider.transactional(
      () -> new HibernatePersonGateway(entityManager, mapper, queryProvider)
    );
  }
}

