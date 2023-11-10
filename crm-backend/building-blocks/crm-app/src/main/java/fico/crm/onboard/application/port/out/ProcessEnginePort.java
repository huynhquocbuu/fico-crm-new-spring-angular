package fico.crm.onboard.application.port.out;

import fico.crm.configuration.exception.BadExecuteProcessException;
import fico.crm.onboard.domain.CallCustomer;

public interface ProcessEnginePort {
    String startOnboarding(String leadId);
    void callCustomer(CallCustomer input) throws BadExecuteProcessException;
}
