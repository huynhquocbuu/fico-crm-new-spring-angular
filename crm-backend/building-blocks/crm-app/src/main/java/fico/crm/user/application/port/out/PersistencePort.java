package fico.crm.user.application.port.out;

import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.domain.UserDto;

import java.util.List;

public interface PersistencePort {
    List<RoleDto>  getAllRoles();
    List<UserDto> getAllUsers();
}
