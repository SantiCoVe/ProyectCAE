package com.ProyectCAE.persistenceLayer.dao;


import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import com.ProyectCAE.persistenceLayer.mapper.DocumentMapper;
import com.ProyectCAE.persistenceLayer.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<DocumentDTO> findById(UUID id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO);
    }

    public List<DocumentDTO> findAll() {
        return documentMapper.toDTOList(documentRepository.findAll());
    }

    public Optional<DocumentDTO> update(UUID id, DocumentUpdateDTO updateDTO) {
        return documentRepository.findById(id)
                .map(existing -> {
                    documentMapper.updateEntityFromDTO(updateDTO, existing);
                    return documentMapper.toDTO(documentRepository.save(existing));
                });
    }

    public boolean deleteById(UUID id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DocumentDTO> findByFolderId(Long folderId) {
        return documentMapper.toDTOList(documentRepository.findByFolderIdFolder(folderId));
    }

    public List<DocumentDTO> findByDocumentTypeId(Long typeId) {
        return documentMapper.toDTOList(documentRepository.findByDocumentTypeIdType(typeId));
    }

    public List<DocumentDTO> findByActive(Boolean active) {
        return documentMapper.toDTOList(documentRepository.findByActive(active));
    }

    public List<DocumentDTO> findByTitleContaining(String title) {
        return documentMapper.toDTOList(documentRepository.findByTitleContainingIgnoreCase(title));
    }

    public List<DocumentDTO> findActiveByFolderId(Long folderId) {
        return documentMapper.toDTOList(documentRepository.findByFolderIdFolderAndActive(folderId, true));
    }

    public List<DocumentDTO> findByFileType(String fileType) {
        return documentMapper.toDTOList(documentRepository.findByFileType(fileType));
    }

    public List<DocumentDTO> findAllByUserId(Long userId) {
        return documentMapper.toDTOList(documentRepository.findAllByUserId(userId));
    }

    public List<DocumentDTO> findActiveDocumentsByUserId(Long userId) {
        return documentMapper.toDTOList(documentRepository.findActiveDocumentsByUserId(userId));
    }

    public Long countActiveDocumentsByFolderId(Long folderId) {
        return documentRepository.countActiveDocumentsByFolderId(folderId);
    }

    public long count() {
        return documentRepository.count();
    }
}
