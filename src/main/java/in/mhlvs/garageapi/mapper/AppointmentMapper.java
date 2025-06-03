package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.AppointmentDTO;
import in.mhlvs.garageapi.entity.AppointmentEntity;
import in.mhlvs.garageapi.resolver.EntityResolver;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface AppointmentMapper {

    @Mappings({
            @Mapping(target = "car", source = "carId", qualifiedByName = "resolveCar"),
            @Mapping(target = "mechanic", source = "mechanicId", qualifiedByName = "resolveMechanic"),
            @Mapping(target = "services", source = "serviceIds", qualifiedByName = "resolveServices")
    })
    AppointmentEntity toEntity(AppointmentDTO dto);

    @InheritInverseConfiguration
    AppointmentDTO toDto(AppointmentEntity entity);
}
