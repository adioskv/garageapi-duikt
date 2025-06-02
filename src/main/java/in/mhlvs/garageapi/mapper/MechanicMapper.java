package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.MechanicDTO;
import in.mhlvs.garageapi.entity.MechanicEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MechanicMapper {
    MechanicEntity toEntity(MechanicDTO dto);
    MechanicDTO toDto(MechanicEntity entity);
}