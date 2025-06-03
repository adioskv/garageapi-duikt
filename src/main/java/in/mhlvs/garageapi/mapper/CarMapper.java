package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.CarDTO;
import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.resolver.EntityResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface CarMapper {

    @Mapping(source = "ownerId", target = "owner", qualifiedByName = "resolveUser")
    CarEntity toEntity(CarDTO dto);

    @Mapping(source = "owner.id", target = "ownerId")
    CarDTO toDto(CarEntity entity);
}
