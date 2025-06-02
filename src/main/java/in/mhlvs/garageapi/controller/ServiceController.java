package in.mhlvs.garageapi.controller;

import in.mhlvs.garageapi.DTO.ServiceDTO;
import in.mhlvs.garageapi.entity.ServiceEntity;
import in.mhlvs.garageapi.mapper.ServiceMapper;
import in.mhlvs.garageapi.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @PostMapping("/create")
    public ResponseEntity<ServiceDTO> create(@RequestBody ServiceDTO dto) {
        ServiceEntity entity = serviceMapper.toEntity(dto);
        return ResponseEntity.ok(serviceMapper.toDto(serviceRepository.save(entity)));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ServiceDTO> get(@PathVariable UUID id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ServiceDTO> getAll() {
        return StreamSupport.stream(serviceRepository.findAll().spliterator(), false)
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceDTO> update(@PathVariable UUID id, @RequestBody ServiceDTO dto) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(dto.getName());
                    service.setCost(dto.getCost());
                    return ResponseEntity.ok(serviceMapper.toDto(serviceRepository.save(service)));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
