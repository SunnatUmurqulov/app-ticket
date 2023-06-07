package uz.pdp.appticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.ResponseToken;
import uz.pdp.appticket.entity.Roles;
import uz.pdp.appticket.entity.User;
import uz.pdp.appticket.entity.enums.RoleName;
import uz.pdp.appticket.exception.MyCustomException;
import uz.pdp.appticket.payload.SignIn;
import uz.pdp.appticket.payload.UserDTO;
import uz.pdp.appticket.repository.AuthRepository;
import uz.pdp.appticket.repository.RoleRepository;
import uz.pdp.appticket.security.JwtTokenProvider;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;

    public HttpEntity<?> register(UserDTO userDTO) {
        boolean exists = authRepository.existsByPhoneNumber(userDTO.getPhoneNumber());
        if (exists) {
            throw new MyCustomException("User by exists");
        }
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow())
                .active(userDTO.isActive()).build();
        authRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Successfully created", true));
    }

    public ResponseToken login(SignIn signIn) {
        User byPhoneNumber = authRepository.findByPhoneNumber(signIn.getPhoneNumber());
        if (byPhoneNumber != null) {
            if (passwordEncoder.matches(signIn.getPassword(), byPhoneNumber.getPassword()) && byPhoneNumber.isActive()) {
                String token = jwtTokenProvider.generateJwtToken(byPhoneNumber.getId());
                return new ResponseToken(token);
            }
        }

        throw new MyCustomException("User not found");
    }

    public HttpEntity<?> getAllUser() {
        List<User> all = authRepository.findAll();
        return ResponseEntity.ok().body(all);
    }

    public HttpEntity<?> deleteUser(Integer id) {
        Optional<User> optionalUser = authRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            authRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("User successfully deleted", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("User not found with this:[" + id + "]", false));
    }

    public HttpEntity<?> update(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = authRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setPassword(userDTO.getPassword());
            authRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("User successfully edited", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("User not found with this:[" + id + "]", false));
    }

    public HttpEntity<?> getUser(Integer id) {
        Optional<User> optionalUser = authRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("User not found with this:[" + id + "]", false));
    }
}
