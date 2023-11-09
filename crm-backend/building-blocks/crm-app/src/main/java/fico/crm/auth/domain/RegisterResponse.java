package fico.crm.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import fico.crm.shared.dtos.RoleDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    Set<RoleDto> roles = new HashSet<>();
}
