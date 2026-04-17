package com.ProyectCAE.persistenceLayer.mapper;


import org.mapstruct.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * Mapper para conversiones entre FolderEntity y DTOs
 *
 * MAPEOS PERSONALIZADOS:
 * - userId: Se extrae de user.idUsuario
 * - userName: Se extrae de user.name (dato denormalizado)
 * - Al crear, userId → UserEntity (solo con ID, JPA resuelve la relación)
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface FolderMapper {

    @Mapping(target = "userId", source = "user.idUsuario")
    @Mapping(target = "userName", source = "user.name")
    FolderDTO toDTO(FolderEntity entity);

    List<FolderDTO> toDTOList(List<FolderEntity> entities);

    @Mapping(target = "idFolder", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "createUserEntityFromId")
    FolderEntity toEntity(FolderCreateDTO createDTO);

    @Mapping(target = "idFolder", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(FolderUpdateDTO updateDTO, @MappingTarget FolderEntity entity);

    @Named("createUserEntityFromId")
    default UserEntity createUserEntityFromId(Long userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setIdUsuario(userId);
        return user;
    }
}
