package fico.crm.configuration.security;

import lombok.Data;

@Data
public class NotAuthResponse {
    private String message;
    private String reason;
}
