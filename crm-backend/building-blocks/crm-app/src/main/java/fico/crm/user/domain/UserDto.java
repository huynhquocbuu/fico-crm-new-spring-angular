package fico.crm.user.domain;


import fico.crm.shared.dtos.RoleDto;
import fico.crm.shared.enums.EAuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String fullName;
    private EAuthType authType;

    Set<RoleDto> roles = new HashSet<>();
}
