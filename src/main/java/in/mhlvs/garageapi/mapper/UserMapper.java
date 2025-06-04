package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.UserDTO;
import in.mhlvs.garageapi.entity.CarEntity;
import in.mhlvs.garageapi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "carIds", target = "cars", qualifiedByName = "idsToCars")
    })
    UserEntity toEntity(UserDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "cars", target = "carIds", qualifiedByName = "carsToIds")
    })
    UserDTO toDto(UserEntity entity);

    @Named("carsToIds")
    static List<UUID> mapCarsToIds(List<in.mhlvs.garageapi.entity.CarEntity> cars) {
        if (cars == null) return null;
        return cars.stream()
                .map(in.mhlvs.garageapi.entity.CarEntity::getId)
                .toList();
    }

    @Named("idsToCars")
    static List<CarEntity> mapIdsToCars(List<UUID> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    var car = new in.mhlvs.garageapi.entity.CarEntity();
                    car.setId(id);
                    return car;
                })
                .toList();
    }
}

