package fico.crm.infrastructure.repositories;

import fico.crm.infrastructure.entities.Role;
import fico.crm.shared.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, ERole> {
}
