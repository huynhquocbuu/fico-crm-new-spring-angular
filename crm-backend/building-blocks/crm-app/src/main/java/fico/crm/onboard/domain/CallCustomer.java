package fico.crm.onboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CallCustomer {
    @JsonProperty("process-instance")
    private String processInstance;
    @JsonProperty("is-accept-advice")
    private boolean isAcceptAdvice;
}
