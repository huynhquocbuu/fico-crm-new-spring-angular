package crm.configuration.persistence;



//@Configuration
//@EnableJdbcRepositories(
//        basePackages = {"crm.infrastructure.repositories"},
//        transactionManagerRef = "crmTransactionManager",
//        jdbcOperationsRef = "crmJdbcOperations")

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "crmEntityManagerFactory",
        transactionManagerRef = "crmTransactionManager",
        basePackages = {"crm.infrastructure.repositories"})
public class CrmJpaPersistenceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.crm")
    public DataSourceProperties crmDataSourceProperties() {
        return new DataSourceProperties();
    }

    //1-
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.crm.hikari")
    public DataSource crmDataSource() {
        return crmDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Primary
    //@Bean(name = "primaryEntityManagerFactory")
    @Bean
    public LocalContainerEntityManagerFactoryBean crmEntityManagerFactory(
            EntityManagerFactoryBuilder crmEntityManagerFactoryBuilder,
            @Qualifier("crmDataSource") DataSource crmDataSource) {

//        Map<String, String> crmJpaProperties = new HashMap<>();
//        crmJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        crmJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return crmEntityManagerFactoryBuilder
                .dataSource(crmDataSource)
                .packages("crm.infrastructure.entities")
                //.persistenceUnit("primaryDataSource")
                //.properties(crmJpaProperties)
                .build();
    }

    @Primary
    //@Bean(name = "primaryTransactionManager")
    @Bean
    public PlatformTransactionManager crmTransactionManager(
            @Qualifier("crmEntityManagerFactory") EntityManagerFactory crmEntityManagerFactory) {

        return new JpaTransactionManager(crmEntityManagerFactory);
    }
}
