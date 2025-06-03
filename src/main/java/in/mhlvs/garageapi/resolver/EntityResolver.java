package in.mhlvs.garageapi.resolver;

import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.entity.MechanicEntity;
import in.mhlvs.garageapi.entity.ServiceEntity;
import in.mhlvs.garageapi.repository.CarRepository;
import in.mhlvs.garageapi.repository.MechanicRepository;
import in.mhlvs.garageapi.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EntityResolver implements AppointmentEntityResolver {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    @Named("resolveCar")
    public CarEntity resolveCar(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + id));
    }

    @Override
    @Named("resolveMechanic")
    public MechanicEntity resolveMechanic(UUID id) {
        return mechanicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mechanic not found: " + id));
    }

    @Override
    @Named("resolveServices")
    public List<ServiceEntity> resolveServices(List<UUID> ids) {
        return ids.stream()
                .map(id -> serviceRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Service not found: " + id)))
                .collect(Collectors.toList());
    }
}
