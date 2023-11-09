package fico.crm.auth.application.port.out;

import fico.crm.infrastructure.entities.Role;
import fico.crm.infrastructure.entities.User;
import fico.crm.shared.enums.ERole;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;

public interface AuthPersistencePort {
    User findAuthUser(String username);
    void checkRegisterUser(String username);
    Set<Role> findRegisterRoles(Set<ERole> roles) throws RoleNotFoundException;

    User insertUser(User userEntity);
}
