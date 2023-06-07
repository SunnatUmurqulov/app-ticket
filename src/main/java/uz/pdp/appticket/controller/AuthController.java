package uz.pdp.appticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.ResponseToken;
import uz.pdp.appticket.payload.SignIn;
import uz.pdp.appticket.payload.UserDTO;
import uz.pdp.appticket.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserDTO userDTO){
        return authService.register(userDTO);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn){
        ResponseToken responseToken = authService.login(signIn);
        return ResponseEntity.status(responseToken != null ? 201:401).body(responseToken);
    }

    @PreAuthorize(value = "hasAuthority('READ_USER')")
    @GetMapping
    public HttpEntity<?> getAllUser(){
        HttpEntity<?> allUser = authService.getAllUser();
        if (allUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("User not found",false));
        }
        return ResponseEntity.ok().body(allUser);
    }

    @PreAuthorize(value = "hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id){
        return authService.getUser(id);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        return authService.deleteUser(id);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        return authService.update(id,userDTO);
    }
}
