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

    UserDTO toDTO(UserEntity entity);

    List<UserDTO> toDTOList(List<UserEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "folders", ignore = true)
    UserEntity toEntity(UserCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "folders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserUpdateDTO updateDTO, @MappingTarget UserEntity entity);
}
