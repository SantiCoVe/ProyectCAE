package com.ProyectCAE.persistenceLayer.dao;


import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import com.ProyectCAE.persistenceLayer.mapper.DocumentMapper;
import com.ProyectCAE.persistenceLayer.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * DAO para operaciones de persistencia de documentos.
 * El DTO de respuesta incluye datos denormalizados de folder y documentType.
 */
@Repository
@RequiredArgsConstructor
public class DocumentDAO {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentDTO save(DocumentCreateDTO createDTO) {
        DocumentEntity entity = documentMapper.toEntity(createDTO);
        DocumentEntity savedEntity = documentRepository.save(entity);
        return documentMapper.toDTO(savedEntity);
    }

    public Optional<DocumentDTO> findById(long id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO);
    }

    public List<DocumentDTO> findAll() {
        return documentMapper.toDTOList(documentRepository.findAll());
    }

    public Optional<DocumentDTO> update(long id, DocumentUpdateDTO updateDTO) {
        return documentRepository.findById(id)
                .map(existing -> {
                    documentMapper.updateEntityFromDTO(updateDTO, existing);
                    return documentMapper.toDTO(documentRepository.save(existing));
                });
    }

    public boolean deleteById(long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DocumentDTO> findByFolderId(long folderId) {
        return documentMapper.toDTOList(documentRepository.findByFolder_Id(folderId));
    }

    public List<DocumentDTO> findByDocumentTypeId(long typeId) {
        return documentMapper.toDTOList(documentRepository.findByDocumentType_IdType(typeId));
    }

    public List<DocumentDTO> findByActive(Boolean active) {
        return documentMapper.toDTOList(documentRepository.findByActive(active));
    }

    public List<DocumentDTO> findByTitleContaining(String title) {
        return documentMapper.toDTOList(documentRepository.findByTitleContainingIgnoreCase(title));
    }

    public List<DocumentDTO> findActiveByFolderId(long folderId) {
        return documentMapper.toDTOList(documentRepository.findByFolder_IdAndActive(folderId, true));
    }

    public List<DocumentDTO> findByFileType(String fileType) {
        return documentMapper.toDTOList(documentRepository.findByFileType(fileType));
    }

    public List<DocumentDTO> findAllByUserId(long userId) {
        return documentMapper.toDTOList(documentRepository.findByCreatedBy_Id(userId));
    }

    public List<DocumentDTO> findActiveDocumentsByUserId(long userId) {
        return documentMapper.toDTOList(documentRepository.findByCreatedBy_IdAndActive(userId, true));
    }

    public long countActiveDocumentsByFolderId(long folderId) {
        return documentRepository.countByFolder_IdAndActive(folderId, true);
    }

    public long count() {
        return documentRepository.count();
    }
}
