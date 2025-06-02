package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.CarDTO;
import in.mhlvs.garageapi.entity.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarEntity toEntity(CarDTO dto);
    CarDTO toDto(CarEntity entity);
}