package crm.infrastructure.entities;


import crm.shared.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole id;

    @Column(length = 50, name = "role_name")
    private String roleName;
    @Column(length = 50, name = "role_desc")
    private String roleDesc;

    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();
}
