package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.UserDTO;
import in.mhlvs.garageapi.entity.UserEntity;
import in.mhlvs.garageapi.mapper.UserMapper;
import in.mhlvs.garageapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        var entity = userMapper.toEntity(user);
        var saved = userRepository.save(entity);
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return ((List<UserEntity>) userRepository.findAll())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/update/{id}")
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
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}