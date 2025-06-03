package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.MechanicDTO;
import in.mhlvs.garageapi.entity.MechanicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MechanicMapper {
    MechanicEntity toEntity(MechanicDTO dto);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "specialization", target = "specialization"),
            @Mapping(source = "appointments", target = "appointments"),
    })
    MechanicDTO toDto(MechanicEntity entity);
}