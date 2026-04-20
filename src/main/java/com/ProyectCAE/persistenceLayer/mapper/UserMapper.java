package com.ProyectCAE.persistenceLayer.mapper;

import com.ProyectCAE.businessLayer.dto.UserCreateDTO;
import com.ProyectCAE.businessLayer.dto.UserDTO;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

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

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "folders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserUpdateDTO updateDTO, @MappingTarget UserEntity entity);
}
