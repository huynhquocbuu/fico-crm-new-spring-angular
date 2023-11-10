package fico.crm.api.common;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import fico.crm.process.application.port.in.ProcessAdminUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/process")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class ProcessAdminApi {
    private ProcessAdminUseCase useCase;
    public ProcessAdminApi(ProcessAdminUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping("/deploy-process")
    public ResponseEntity<ResponseWrapper<String>> login
            (@RequestParam(name = "process-file") MultipartFile processFile,
             @RequestParam(name = "process-name") String processName,
             @RequestParam(name = "version") String versionName) throws Exception {
        InputStream processInputStream = processFile.getInputStream();
        String processId = useCase.deploy(
                "fico-crm-wf",
                processName,
                versionName,
                processInputStream);
        processInputStream.close();
        log.info("Deployment Id = " + processId);
        return ResponseHandler.success("ok deploy-process");
    }
}
