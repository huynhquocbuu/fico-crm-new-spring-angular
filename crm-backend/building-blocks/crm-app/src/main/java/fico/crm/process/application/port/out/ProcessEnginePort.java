package fico.crm.process.application.port.out;

import org.activiti.engine.repository.Deployment;

import java.io.InputStream;

public interface ProcessEnginePort {
    Deployment deployProcess(String processCategory, String processName, String version, InputStream processInputStream);
}
