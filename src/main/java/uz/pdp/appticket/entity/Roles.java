package uz.pdp.appticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appticket.entity.enums.PermissionName;
import uz.pdp.appticket.entity.enums.RoleName;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PermissionName> permissionNames;

    public  Roles(String name,RoleName roleName, Set<PermissionName> values) {
        this.name = name;
        this.permissionNames = values;
        this.roleName = roleName;
    }

    public Roles(RoleName roleName) {
        this.roleName = roleName;
    }
}
