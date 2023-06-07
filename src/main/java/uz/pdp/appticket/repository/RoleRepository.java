package uz.pdp.appticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appticket.entity.Roles;
import uz.pdp.appticket.entity.enums.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(RoleName roleName);
}
