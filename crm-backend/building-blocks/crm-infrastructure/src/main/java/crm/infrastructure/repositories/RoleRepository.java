package crm.infrastructure.repositories;

import crm.infrastructure.entities.Role;
import crm.shared.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, ERole> {
}
