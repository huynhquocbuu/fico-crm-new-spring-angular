package fico.crm.configuration.common;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class CrmDataSourceConfig {
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
}
