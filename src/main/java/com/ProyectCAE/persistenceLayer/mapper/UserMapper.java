package com.ProyectCAE.persistenceLayer.mapper;

import com.ProyectCAE.businessLayer.dto.userDTOs.UserCreateDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre UserEntity y DTOs
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    @Mapping(target = "idUsuario", source = "id")
    @Mapping(target = "updateDate", source = "updateDate")
    UserDTO toDTO(UserEntity entity);

    List<UserDTO> toDTOList(List<UserEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "folders", ignore = true)
    UserEntity toEntity(UserCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "folders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserUpdateDTO updateDTO, @MappingTarget UserEntity entity);
}
