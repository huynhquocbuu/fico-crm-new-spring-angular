package fico.crm.configuration.common;

import lombok.extern.slf4j.Slf4j;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class ProcessEngineFactoryConfig {
    @Bean
    public PlatformTransactionManager processEngineTransactionManager(
            @Qualifier("processEngineDataSource") DataSource processEngineDataSource) {
        return new DataSourceTransactionManager(processEngineDataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(
            @Qualifier("processEngineDataSource") DataSource processEngineDataSource,
            @Qualifier("processEngineTransactionManager") PlatformTransactionManager processEngineTransactionManager) {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();

        config.setDataSource(processEngineDataSource);
        config.setTransactionManager(processEngineTransactionManager);

        config.setDatabaseSchemaUpdate("create");
        config.setHistory("audit");
        config.setJobExecutorActivate(true);
        config.setDatabaseSchemaUpdate("false");
        config.setAsyncExecutorActivate(true);
        config.setJobExecutorActivate(false);

        return config;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactory(
            SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
        factoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        log.info("instance ProcessEngineFactoryBean SUCCESS!!!" + factoryBean.toString());
        return factoryBean;
    }

//    @Bean
//    @Primary
//    public ProcessEngineConfiguration processEngineConfiguration
//            (@Qualifier("processEngineDataSource") DataSource dataSource){
//        return SpringProcessEngineConfiguration
//                //.createProcessEngineConfigurationFromResourceDefault()
//                .createStandaloneProcessEngineConfiguration()
//                .setDataSource(dataSource)
//                .setAsyncExecutorEnabled(true);
//    }
//
//    @Bean
//    public ProcessEngine processEngine(
//            @Qualifier("processEngineDataSource") DataSource dataSource) {
//        var config = SpringProcessEngineConfiguration
//                .createStandaloneProcessEngineConfiguration()
//                .setDataSource(dataSource);
//        ProcessEngine output = config.buildProcessEngine();
//        log.info("start ProcessEngine BEAN SUCCESS!!!" + output.toString());
//        return output;
//    }
}
