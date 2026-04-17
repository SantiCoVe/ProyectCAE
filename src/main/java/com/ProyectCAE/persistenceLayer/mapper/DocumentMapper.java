package com.ProyectCAE.persistenceLayer.mapper;

import com.tuempresa.gestor.businessLayer.dto.document.DocumentCreateDTO;
import com.tuempresa.gestor.businessLayer.dto.document.DocumentDTO;
import com.tuempresa.gestor.businessLayer.dto.document.DocumentUpdateDTO;
import com.tuempresa.gestor.persistenceLayer.entity.DocumentEntity;
import com.tuempresa.gestor.persistenceLayer.entity.DocumentTypeEntity;
import com.tuempresa.gestor.persistenceLayer.entity.FolderEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper para conversiones entre DocumentEntity y DTOs
 *
 * MAPEOS PERSONALIZADOS:
 * - folderId / folderName: extraídos de folder.*
 * - documentTypeId / documentTypeName: extraídos de documentType.*
 * - Al crear, folderId → FolderEntity y documentTypeId → DocumentTypeEntity
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface DocumentMapper {

    @Mapping(target = "folderId", source = "folder.idFolder")
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
    @Mapping(target = "folder", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DocumentUpdateDTO updateDTO, @MappingTarget DocumentEntity entity);

    @Named("createFolderEntityFromId")
    default FolderEntity createFolderEntityFromId(Long folderId) {
        if (folderId == null) return null;
        FolderEntity folder = new FolderEntity();
        folder.setIdFolder(folderId);
        return folder;
    }

    @Named("createDocumentTypeEntityFromId")
    default DocumentTypeEntity createDocumentTypeEntityFromId(Long typeId) {
        if (typeId == null) return null;
        DocumentTypeEntity type = new DocumentTypeEntity();
        type.setIdType(typeId);
        return type;
    }
}
