package fico.crm.api.onboard;

import fico.crm.configuration.exception.BadExecuteProcessException;
import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import fico.crm.onboard.application.port.in.LeadOnboardingUseCase;
import fico.crm.onboard.domain.CallCustomer;
import fico.crm.onboard.domain.StartOnboarding;
import fico.crm.shared.dtos.ProcessExecuteOutput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/lead/onboard")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class LeadOnboardingApi {
    private LeadOnboardingUseCase useCase;
    public LeadOnboardingApi(LeadOnboardingUseCase useCase){
        this.useCase = useCase;
    }
    @PostMapping("/start-onboarding")
    public ResponseEntity<ResponseWrapper<ProcessExecuteOutput>> startOnboarding
            (@Valid @RequestBody StartOnboarding input) {
        return ResponseHandler.success(useCase.startOnboarding(input));
    }

    @PostMapping("/call-customer")
    public ResponseEntity<ResponseWrapper<ProcessExecuteOutput>> callCustomer
            (@Valid @RequestBody CallCustomer input) throws BadExecuteProcessException {
        return ResponseHandler.success(useCase.callCustomer(input));
    }
}
