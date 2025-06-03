package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.AppointmentDTO;
import in.mhlvs.garageapi.entity.AppointmentEntity;
import in.mhlvs.garageapi.mapper.AppointmentMapper;
import in.mhlvs.garageapi.repository.AppointmentRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO dto) {
        AppointmentEntity entity = appointmentMapper.toEntity(dto);
        return ResponseEntity.ok(
                appointmentMapper.toDto(appointmentRepository.save(entity))
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppointmentDTO> get(@PathVariable UUID id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AppointmentDTO> getAll() {
        return ((List<AppointmentEntity>) appointmentRepository.findAll())
                .stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable UUID id, @RequestBody AppointmentDTO dto) {
        return appointmentRepository.findById(id)
                .map(appt -> {
                    appt.setDateTime(dto.getDateTime());
                    appt.setStatus(dto.getStatus());
                    return ResponseEntity.ok(appointmentMapper.toDto(appointmentRepository.save(appt)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
