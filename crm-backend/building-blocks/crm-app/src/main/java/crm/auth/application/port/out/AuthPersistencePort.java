package crm.auth.application.port.out;

import crm.infrastructure.entities.Role;
import crm.infrastructure.entities.User;
import crm.shared.enums.ERole;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;
import java.util.Set;

public interface AuthPersistencePort {
    User findAuthUser(String username);
    void checkRegisterUser(String username);
    Set<Role> findRegisterRoles(Set<ERole> roles) throws RoleNotFoundException;

    User insertUser(User userEntity);
}
