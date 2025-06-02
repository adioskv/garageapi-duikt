package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.ServiceDTO;
import in.mhlvs.garageapi.entity.ServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceEntity toEntity(ServiceDTO dto);
    ServiceDTO toDto(ServiceEntity entity);
}
