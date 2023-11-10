package fico.crm.shared.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessExecuteOutput {
    @JsonProperty("process-name")
    private String processName;
    @JsonProperty("process-instance")
    private String processInstance;
    private String message;
}