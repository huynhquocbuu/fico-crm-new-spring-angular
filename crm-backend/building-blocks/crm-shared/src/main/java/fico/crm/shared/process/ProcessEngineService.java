package fico.crm.shared.process;

import lombok.Getter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProcessEngineService {
    private ProcessEngine processEngine;
    private RepositoryService repositoryService;
    private TaskService taskService;
    private RuntimeService runtimeService;
    public ProcessEngineService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        processEngine = processEngineFactory.getObject();
        repositoryService = processEngine.getRepositoryService();
        taskService = processEngine.getTaskService();
        runtimeService = processEngine.getRuntimeService();
    }
}
