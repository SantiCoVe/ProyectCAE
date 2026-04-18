package com.ProyectCAE.persistenceLayer.mapper;


import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import org.mapstruct.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * Mapper para conversiones entre DocumentTypeEntity y DTOs
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface DocumentTypeMapper {

    DocumentTypeDTO toDTO(DocumentTypeEntity entity);

    List<DocumentTypeDTO> toDTOList(List<DocumentTypeEntity> entities);

    @Mapping(target = "idType", ignore = true)
    @Mapping(target = "createrDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    DocumentTypeEntity toEntity(DocumentTypeCreateDTO createDTO);

    @Mapping(target = "idType", ignore = true)
    @Mapping(target = "createrDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DocumentTypeUpdateDTO updateDTO, @MappingTarget DocumentTypeEntity entity);
}
