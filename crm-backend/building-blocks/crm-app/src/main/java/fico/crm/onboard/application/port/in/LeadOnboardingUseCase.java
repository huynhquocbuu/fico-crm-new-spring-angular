package fico.crm.onboard.application.port.in;

import fico.crm.configuration.exception.BadExecuteProcessException;
import fico.crm.onboard.domain.CallCustomer;
import fico.crm.onboard.domain.StartOnboarding;
import fico.crm.shared.dtos.ProcessExecuteOutput;

public interface LeadOnboardingUseCase {
    ProcessExecuteOutput startOnboarding(StartOnboarding input);
    ProcessExecuteOutput callCustomer(CallCustomer input) throws BadExecuteProcessException;
}
