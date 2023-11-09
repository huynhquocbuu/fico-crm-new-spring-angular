package fico.crm.process.application.service;

import fico.crm.process.application.port.in.ProcessAdminUseCase;
import fico.crm.process.application.port.out.ProcessEnginePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ProcessAdminService implements ProcessAdminUseCase {

    private ProcessEnginePort processEnginePort;
    public ProcessAdminService(
            @Qualifier("ProcessEnginePort4ProcessDomain") ProcessEnginePort processEnginePort){
        this.processEnginePort = processEnginePort;
    }
    @Override
    public String deploy(String processCategory, String processName, String version, InputStream processInputStream){
        var deployment = processEnginePort
                .deployProcess(processCategory, processName, version, processInputStream);
        return deployment.getId();
    }
}
