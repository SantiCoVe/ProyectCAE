package com.ProyectCAE.persistenceLayer.mapper;


import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre DocumentEntity y DTOs
 *
 * MAPEOS PERSONALIZADOS:
 * - folderId / folderName: extraídos de folder.*
 * - documentTypeId / documentTypeName: extraídos de documentType.*
 * - createdById: extraído de createdBy.id
 * - Al crear/actualizar, folderId → FolderEntity, documentTypeId → DocumentTypeEntity, createdById → UserEntity
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface DocumentMapper {

    @Mapping(target = "folderId", source = "folder.id")
    @Mapping(target = "folderName", source = "folder.name")
    @Mapping(target = "documentTypeId", source = "documentType.idType")
    @Mapping(target = "documentTypeName", source = "documentType.name")
    DocumentDTO toDTO(DocumentEntity entity);

    List<DocumentDTO> toDTOList(List<DocumentEntity> entities);

    @Mapping(target = "idDocument", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "folder", source = "folderId", qualifiedByName = "createFolderEntityFromId")
    @Mapping(target = "documentType", source = "documentTypeId", qualifiedByName = "createDocumentTypeEntityFromId")
    DocumentEntity toEntity(DocumentCreateDTO createDTO);

    @Mapping(target = "idDocument", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "folder", source = "folderId", qualifiedByName = "createFolderEntityFromId")
    @Mapping(target = "documentType", source = "documentTypeId", qualifiedByName = "createDocumentTypeEntityFromId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DocumentUpdateDTO updateDTO, @MappingTarget DocumentEntity entity);

    @Named("createFolderEntityFromId")
    default FolderEntity createFolderEntityFromId(Integer folderId) {
        if (folderId == null) return null;
        FolderEntity folder = new FolderEntity();
        folder.setId(folderId);
        return folder;
    }

    @Named("createDocumentTypeEntityFromId")
    default DocumentTypeEntity createDocumentTypeEntityFromId(Integer typeId) {
        if (typeId == null) return null;
        DocumentTypeEntity type = new DocumentTypeEntity();
        type.setIdType(typeId);
        return type;
    }

}
