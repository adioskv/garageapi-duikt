package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.AppointmentDTO;
import in.mhlvs.garageapi.entity.AppointmentEntity;
import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.entity.MechanicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mappings({
            @Mapping(source = "carId", target = "car.id"),
            @Mapping(source = "mechanicId", target = "mechanic.id"),
            @Mapping(target = "services", ignore = true)
    })
    AppointmentEntity toEntity(AppointmentDTO dto);

    @Mappings({
            @Mapping(source = "car.id", target = "carId"),
            @Mapping(source = "mechanic.id", target = "mechanicId"),
            @Mapping(target = "serviceIds", ignore = true)
    })
    AppointmentDTO toDto(AppointmentEntity entity);
}