package fico.crm.onboard.application.service;

import fico.crm.configuration.exception.BadExecuteProcessException;
import fico.crm.onboard.application.port.in.LeadOnboardingUseCase;
import fico.crm.onboard.application.port.out.ProcessEnginePort;
import fico.crm.onboard.domain.CallCustomer;
import fico.crm.onboard.domain.StartOnboarding;
import fico.crm.shared.constants.ProcessConstant;
import fico.crm.shared.dtos.ProcessExecuteOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeadOnboardingService implements LeadOnboardingUseCase {
    private final ProcessEnginePort processEnginePort;
    public LeadOnboardingService(
            @Qualifier("ProcessEnginePort4LeadOnboard") ProcessEnginePort processEnginePort){
        this.processEnginePort = processEnginePort;
    }
    @Override
    public ProcessExecuteOutput startOnboarding(StartOnboarding input) {
        String processInstance = processEnginePort
                .startOnboarding(input.getLeadId());
        log.info(ProcessConstant.Process_Name + " processInstanceId = " + processInstance);
        return ProcessExecuteOutput.builder()
                .processName(ProcessConstant.Process_Name)
                .processInstance(processInstance)
                .message("Success")
                .build();
    }

    @Override
    public ProcessExecuteOutput callCustomer(CallCustomer input) throws BadExecuteProcessException {
        processEnginePort.callCustomer(input);
        return ProcessExecuteOutput.builder()
                .processName(ProcessConstant.Process_Name)
                .processInstance(input.getProcessInstance())
                .message("Success")
                .build();
    }
}
