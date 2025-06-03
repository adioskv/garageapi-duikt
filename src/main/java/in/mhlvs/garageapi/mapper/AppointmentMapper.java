package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.AppointmentDTO;
import in.mhlvs.garageapi.entity.AppointmentEntity;
import in.mhlvs.garageapi.resolver.EntityResolver;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface AppointmentMapper {

    @Mappings({
            @Mapping(source = "carId", target = "car", qualifiedByName = "resolveCar"),
            @Mapping(source = "mechanicId", target = "mechanic", qualifiedByName = "resolveMechanic"),
            @Mapping(source = "serviceIds", target = "services", qualifiedByName = "resolveServices")
    })
    AppointmentEntity toEntity(AppointmentDTO dto);

    @Mappings({
            @Mapping(source = "car.id", target = "carId"),
            @Mapping(source = "mechanic.id", target = "mechanicId"),
            @Mapping(source = "services", target = "serviceIds", qualifiedByName = "mapServiceEntitiesToIds")
    })
    AppointmentDTO toDto(AppointmentEntity entity);
}
