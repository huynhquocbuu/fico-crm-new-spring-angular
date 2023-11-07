package crm.configuration.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseWrapper <T> {
    private String status;
    @JsonProperty("error-code")
    private String errorCode;

    @JsonProperty("error-message")
    private String errorMessage;
    private T data;
}
