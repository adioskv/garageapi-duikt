package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.MechanicDTO;
import in.mhlvs.garageapi.entity.MechanicEntity;
import in.mhlvs.garageapi.mapper.MechanicMapper;
import in.mhlvs.garageapi.repository.MechanicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/mechanics")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class MechanicController {
    private final MechanicRepository mechanicRepository;
    private final MechanicMapper mechanicMapper;

    @PostMapping("/create")
    public ResponseEntity<MechanicDTO> create(@RequestBody MechanicDTO dto) {
        MechanicEntity entity = mechanicMapper.toEntity(dto);
        return ResponseEntity.ok(mechanicMapper.toDto(mechanicRepository.save(entity)));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MechanicDTO> get(@PathVariable UUID id) {
        return mechanicRepository.findById(id)
                .map(mechanicMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MechanicDTO> getAll() {
        return StreamSupport.stream(mechanicRepository.findAll().spliterator(), false)
                .map(mechanicMapper::toDto)
                .collect(Collectors.toList());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<MechanicDTO> update(@PathVariable UUID id, @RequestBody MechanicDTO dto) {
        return mechanicRepository.findById(id)
                .map(mech -> {
                    mech.setName(dto.getName());
                    mech.setSpecialization(dto.getSpecialization());
                    return ResponseEntity.ok(mechanicMapper.toDto(mechanicRepository.save(mech)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (mechanicRepository.existsById(id)) {
            mechanicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}