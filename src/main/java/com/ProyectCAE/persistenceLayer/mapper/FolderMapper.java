package com.ProyectCAE.persistenceLayer.mapper;

import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderCreateDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre FolderEntity y DTOs
 *
 * El DTO de respuesta incluye datos denormalizados del usuario (userId, userNames)
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface FolderMapper {

    @Mapping(target = "userId", source = "createdBy.id")
    @Mapping(target = "userNames", source = "createdBy.names")
    @Mapping(target = "documentCount", expression = "java(entity.getDocuments() == null ? 0L : (long) entity.getDocuments().size())")
    FolderDTO toDTO(FolderEntity entity);

    List<FolderDTO> toDTOList(List<FolderEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "createdBy", source = "userId", qualifiedByName = "createUserEntityFromId")
    FolderEntity toEntity(FolderCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(FolderUpdateDTO updateDTO, @MappingTarget FolderEntity entity);

    @Named("createUserEntityFromId")
    default UserEntity createUserEntityFromId(Long userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }
}
