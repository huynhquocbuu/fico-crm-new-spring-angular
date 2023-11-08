package crm.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.infrastructure.entities.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String username;
    @JsonProperty("full-name")
    private String fullName;
    private Set<RoleDto> roles;

    @JsonProperty("access-token")
    private String accessToken;

    @JsonProperty("auth-type")
    private String authType = "Bearer jwt";

//    public LoginResponse(String accessToken, String username, String fullName, Set<Role> roles){
//        this.username = username;
//        this.fullName = fullName;
//        this.roles = roles;
//        this.accessToken = accessToken;
//    }
}
