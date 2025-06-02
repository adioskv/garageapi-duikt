package in.mhlvs.garageapi.mapper;

import in.mhlvs.garageapi.DTO.UserDTO;
import in.mhlvs.garageapi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserEntity toEntity(UserDTO dto);

    @Mappings({
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserDTO toDto(UserEntity entity);
}
