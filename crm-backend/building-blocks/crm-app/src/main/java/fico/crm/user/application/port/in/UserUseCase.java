package fico.crm.user.application.port.in;

import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.domain.UserDto;

import java.util.List;

public interface UserUseCase {
    List<RoleDto> getAllRoles();
    List<UserDto> getAllUsers();
}
