package in.mhlvs.garageapi.resolver;

import in.mhlvs.garageapi.entity.*;
import in.mhlvs.garageapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EntityResolver {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Named("resolveCar")
    public CarEntity resolveCar(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + id));
    }

    @Named("mapServiceEntitiesToIds")
    public List<UUID> mapServiceEntitiesToIds(List<ServiceEntity> services) {
        return services.stream().map(ServiceEntity::getId).toList();
    }


    @Named("resolveUser")
    public UserEntity resolveUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    @Named("resolveMechanic")
    public MechanicEntity resolveMechanic(UUID id) {
        return mechanicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mechanic not found: " + id));
    }


    @Named("resolveServices")
    public List<ServiceEntity> resolveServices(List<UUID> ids) {
        return ids.stream()
                .map(id -> serviceRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Service not found: " + id)))
                .toList();
    }

    @Named("resolveAppointment")
    public AppointmentEntity resolveAppointment(UUID id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + id));
    }
}
