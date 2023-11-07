package crm.infrastructure.entities;

import crm.shared.enums.ERole;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_ROLES")
public class RoleRef {
    private ERole roleId;
}
