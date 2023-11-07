package crm.infrastructure.entities;


import crm.shared.enums.ERole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ROLES")
@Getter
@Setter
@Builder
public class Role {
    @Id
    private ERole id;
    private String roleName;
    private String roleDesc;
}
