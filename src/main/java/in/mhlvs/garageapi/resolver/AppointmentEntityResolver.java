package in.mhlvs.garageapi.resolver;

import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.entity.MechanicEntity;
import in.mhlvs.garageapi.entity.ServiceEntity;

import java.util.List;
import java.util.UUID;

public interface AppointmentEntityResolver {
    CarEntity resolveCar(UUID id);
    MechanicEntity resolveMechanic(UUID id);
    List<ServiceEntity> resolveServices(List<UUID> ids);
}
