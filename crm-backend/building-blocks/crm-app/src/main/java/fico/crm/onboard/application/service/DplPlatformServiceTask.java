package fico.crm.onboard.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("dplPlatform")
@Slf4j
public class DplPlatformServiceTask {
    public void submit(String lead_id){
        log.info("===DplPlatformServiceTask.submit ==== lead_id: " + lead_id);
    }
}