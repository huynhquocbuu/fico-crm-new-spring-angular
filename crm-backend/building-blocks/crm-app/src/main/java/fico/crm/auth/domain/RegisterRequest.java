package fico.crm.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import fico.crm.shared.enums.ERole;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    @JsonProperty("full-name")
    private String fullName;
    @Email
    private String email;
    Set<ERole> roles = new HashSet<>();
}
