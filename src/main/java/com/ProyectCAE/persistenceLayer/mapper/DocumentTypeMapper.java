package com.ProyectCAE.persistenceLayer.mapper;


import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeDTO;
import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre DocumentTypeEntity y DTOs
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface DocumentTypeMapper {

    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "createdByNames", source = "createdBy.names")
    DocumentTypeDTO toDTO(DocumentTypeEntity entity);

    List<DocumentTypeDTO> toDTOList(List<DocumentTypeEntity> entities);

    @Mapping(target = "idType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "createdBy", source = "createdById", qualifiedByName = "createUserEntityFromId")
    DocumentTypeEntity toEntity(DocumentTypeCreateDTO createDTO);

    @Mapping(target = "idType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DocumentTypeUpdateDTO updateDTO, @MappingTarget DocumentTypeEntity entity);

    @Named("createUserEntityFromId")
    default UserEntity createUserEntityFromId(Long userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }
}
