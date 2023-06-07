package uz.pdp.appticket.component;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appticket.entity.Roles;
import uz.pdp.appticket.entity.User;
import uz.pdp.appticket.entity.enums.PermissionName;
import uz.pdp.appticket.entity.enums.RoleName;
import uz.pdp.appticket.repository.AuthRepository;
import uz.pdp.appticket.repository.RoleRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
//        Roles superAdmin = roleRepository.save(new Roles(
//                "Super Admin",
//                RoleName.ROLE_ADMIN,
//                Set.of(PermissionName.values())
//        ));
//
//        Roles admin = roleRepository.save(new Roles(
//                "admin",
//                RoleName.ROLE_ADMIN,
//                Set.of(PermissionName.DELETE_USER,
//                        PermissionName.CREATE_USER,
//                        PermissionName.READ_USER,
//                        PermissionName.UPDATE_USER)
//        ));
//
//        Roles user = roleRepository.save(new Roles(
//                "user",
//                RoleName.ROLE_USER,
//                Set.of(PermissionName.READ_EVENT,
//                        PermissionName.BUY_TICKET)
//        ));
//
//        User user1 = new User();
//        user1.setFirstName("Super Admin");
//        user1.setLastName("Super Admin");
//        user1.setPhoneNumber("+998901112233");
//        user1.setPassword(passwordEncoder.encode( "superAdmin"));
//        user1.setRoles(superAdmin);
//
//
//        User user2 = new User();
//        user2.setFirstName("Admin");
//        user2.setLastName("Admin");
//        user2.setPhoneNumber("+998902223344");
//        user2.setPassword(passwordEncoder.encode("admin123"));
//        user2.setRoles(admin);
//
//        roleRepository.save(user);
//        authRepository.save(user1);
//        authRepository.save(user2);
    }


}
