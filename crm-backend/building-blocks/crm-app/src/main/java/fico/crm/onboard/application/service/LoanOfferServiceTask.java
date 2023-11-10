package fico.crm.onboard.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("loanOffer")
@Slf4j
public class LoanOfferServiceTask {

    public void getLoanOffers(String lead_id){

        log.info("==== LoanOfferServiceTask.getLoanOffers ===  lead_id: " + lead_id);
    }
}
//${dplPlatform.submit(lead_id)}