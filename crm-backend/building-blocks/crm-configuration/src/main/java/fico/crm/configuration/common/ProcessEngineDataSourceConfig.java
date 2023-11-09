package fico.crm.configuration.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class ProcessEngineDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.process-engine")
    public DataSourceProperties processEngineDataSourceProperties() {
        return new DataSourceProperties();
    }

    //1-
    @Bean
    @ConfigurationProperties("spring.datasource.process-engine.hikari")
    public DataSource processEngineDataSource() {
        log.info("processEngineDataSource bean ready!!!");
        return processEngineDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
