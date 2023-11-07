package crm.configuration.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(
        basePackages = {"crm.infrastructure.repositories"},
        transactionManagerRef = "crmTransactionManager",
        jdbcOperationsRef = "crmJdbcOperations")
public class CrmPersistenceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.crm")
    public DataSourceProperties crmDataSourceProperties() {
        return new DataSourceProperties();
    }

    //1-
    @Bean
    @ConfigurationProperties("spring.datasource.crm.hikari")
    public DataSource crmDataSource() {
        return crmDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Bean
    public NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate(
            @Qualifier("crmDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    @Bean
    public JdbcTemplate crmJdbcTemplate(
            @Qualifier("crmNamedParameterJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return namedParameterJdbcTemplate.getJdbcTemplate();
    }

    //2
    @Bean
    @Primary
    NamedParameterJdbcOperations crmJdbcOperations(
            @Qualifier("crmNamedParameterJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        return namedParameterJdbcTemplate;
    }
    //3
    @Bean
    PlatformTransactionManager crmTransactionManager(@Qualifier("crmDataSource") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
