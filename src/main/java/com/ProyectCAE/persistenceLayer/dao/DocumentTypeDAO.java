package com.ProyectCAE.persistenceLayer.dao;


import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import com.ProyectCAE.persistenceLayer.mapper.DocumentTypeMapper;
import com.ProyectCAE.persistenceLayer.repository.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO para operaciones de persistencia de tipos de documento.
 */
@Repository
@RequiredArgsConstructor
public class DocumentTypeDAO {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeDTO save(DocumentTypeCreateDTO createDTO) {
        DocumentTypeEntity entity = documentTypeMapper.toEntity(createDTO);
        DocumentTypeEntity savedEntity = documentTypeRepository.save(entity);
        return documentTypeMapper.toDTO(savedEntity);
    }

    public Optional<DocumentTypeDTO> findById(Long id) {
        return documentTypeRepository.findById(id)
                .map(documentTypeMapper::toDTO);
    }

    public List<DocumentTypeDTO> findAll() {
        return documentTypeMapper.toDTOList(documentTypeRepository.findAll());
    }

    public Optional<DocumentTypeDTO> update(Long id, DocumentTypeUpdateDTO updateDTO) {
        return documentTypeRepository.findById(id)
                .map(existing -> {
                    documentTypeMapper.updateEntityFromDTO(updateDTO, existing);
                    return documentTypeMapper.toDTO(documentTypeRepository.save(existing));
                });
    }

    public boolean deleteById(Long id) {
        if (documentTypeRepository.existsById(id)) {
            documentTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsByName(String name) {
        return documentTypeRepository.existsByName(name);
    }

    public Optional<DocumentTypeDTO> findByName(String name) {
        return documentTypeRepository.findByName(name)
                .map(documentTypeMapper::toDTO);
    }

    public List<DocumentTypeDTO> findByActivate(Boolean activate) {
        return documentTypeMapper.toDTOList(documentTypeRepository.findByActivate(activate));
    }

    public List<DocumentTypeDTO> findGlobalTypes() {
        return documentTypeMapper.toDTOList(documentTypeRepository.findByIsGlobal(true));
    }

    public List<DocumentTypeDTO> findActiveGlobalTypes() {
        return documentTypeMapper.toDTOList(documentTypeRepository.findByActivateAndIsGlobal(true, true));
    }

    public List<DocumentTypeDTO> findTypesInUse() {
        return documentTypeMapper.toDTOList(documentTypeRepository.findTypesInUse());
    }

    public long count() {
        return documentTypeRepository.count();
    }
}
