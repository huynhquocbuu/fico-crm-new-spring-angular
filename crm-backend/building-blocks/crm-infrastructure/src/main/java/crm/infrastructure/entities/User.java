package crm.infrastructure.entities;

import crm.infrastructure.entities.embedded.AuditInfoEmbedded;
import crm.shared.enums.EAuthType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table("USERS")
@Getter
@Setter
@Builder
public class User {
    @Id
    private Long id;
    private String username;
    private String fullName;
    private EAuthType authType;
    private String email;
    private String password;

    @MappedCollection(idColumn = "USER_ID", keyColumn = "USER_ID")
    private Set<RoleRef> roles = new HashSet<>();

    @Embedded.Nullable
    private AuditInfoEmbedded auditInfo;
}
