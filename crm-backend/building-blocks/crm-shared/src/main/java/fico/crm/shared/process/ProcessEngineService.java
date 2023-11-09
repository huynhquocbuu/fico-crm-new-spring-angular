package fico.crm.shared.process;

import lombok.Getter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProcessEngineService {
    private ProcessEngine processEngine;
    private RepositoryService repositoryService;
//    private TaskService processEngineTaskService;
//    private RuntimeService processEngineRuntimeService;
    public ProcessEngineService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        processEngine = processEngineFactory
                .getObject();
        repositoryService = processEngine
                .getRepositoryService();
//        processEngineTaskService = processEngine
//                .getTaskService();
//        processEngineRuntimeService = processEngine.getRuntimeService();
    }
}
