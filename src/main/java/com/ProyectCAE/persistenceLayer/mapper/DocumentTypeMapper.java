package com.ProyectCAE.persistenceLayer.mapper;


import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
