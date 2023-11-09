package fico.crm.process.adapters;

import fico.crm.process.application.port.out.ProcessEnginePort;
import fico.crm.shared.process.ProcessEngineService;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("ProcessEnginePort4ProcessDomain")
public class ProcessEngineAdapter implements ProcessEnginePort {
    private ProcessEngineService processEngineService;
    public ProcessEngineAdapter(ProcessEngineService processEngineService){
        this.processEngineService = processEngineService;
    }
    @Override
    public Deployment deployProcess(String processCategory, String processName, String version, InputStream processInputStream){
        return processEngineService.getRepositoryService()
                .createDeployment()
                .category(processCategory)
                .name(processName + "-" + version)
                .addInputStream(processName + ".bpmn20.xml", processInputStream)
                .deploy();
    }
}
