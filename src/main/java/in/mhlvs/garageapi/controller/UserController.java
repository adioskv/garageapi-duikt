package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.UserDTO;
import in.mhlvs.garageapi.entity.UserEntity;
import in.mhlvs.garageapi.mapper.UserMapper;
import in.mhlvs.garageapi.repository.UserRepository;
import in.mhlvs.garageapi.security.JwtUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    @PostMapping("/register")
    @SecurityRequirements({})
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        var user = UserEntity.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role("USER")
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    @SecurityRequirements({})
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var token = jwtUtils.generateJwtToken(request.getUsername());
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var userDto = userMapper.toDto(user);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", userDto
        ));
    }


    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @SecurityRequirement(name = "BearerAuth")
    public List<UserDTO> getUsers() {
        return ((List<UserEntity>) userRepository.findAll())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO dto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFullName(dto.getFullName());
                    user.setEmail(dto.getEmail());
                    user.setPassword(dto.getPassword());
                    return ResponseEntity.ok(userMapper.toDto(userRepository.save(user)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Data
    public static class RegisterRequest {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        private String fullName;
        private String email;
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }
}
