package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.CarDTO;
import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.entity.UserEntity;
import in.mhlvs.garageapi.mapper.CarMapper;
import in.mhlvs.garageapi.repository.CarRepository;
import in.mhlvs.garageapi.repository.UserRepository;
import in.mhlvs.garageapi.security.JwtUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class CarController {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping("/create")
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO dto, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtils.getUsernameFromJwtToken(token);
        UserEntity owner = userRepository.findByUsername(username).orElseThrow();

        CarEntity entity = carMapper.toEntity(dto);

        entity.setId(null);
        entity.setOwner(owner);

        return ResponseEntity.ok(carMapper.toDto(carRepository.save(entity)));
    }




    @GetMapping("/find/{id}")
    public ResponseEntity<CarDTO> get(@PathVariable UUID id) {
        return carRepository.findById(id)
                .map(carMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CarDTO> getAll() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable UUID id, @RequestBody CarDTO dto) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setLicensePlate(dto.getLicensePlate());
                    car.setModel(dto.getModel());
                    return ResponseEntity.ok(carMapper.toDto(carRepository.save(car)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}