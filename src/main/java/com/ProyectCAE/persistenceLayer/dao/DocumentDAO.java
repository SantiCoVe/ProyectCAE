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

    public Optional<DocumentDTO> findById(Integer id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO);
    }

    public List<DocumentDTO> findAll() {
        return documentMapper.toDTOList(documentRepository.findAll());
    }

    public Optional<DocumentDTO> update(Integer id, DocumentUpdateDTO updateDTO) {
        return documentRepository.findById(id)
                .map(existing -> {
                    documentMapper.updateEntityFromDTO(updateDTO, existing);
                    return documentMapper.toDTO(documentRepository.save(existing));
                });
    }

    public boolean deleteById(Integer id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DocumentDTO> findByFolderId(Integer folderId) {
        return documentMapper.toDTOList(documentRepository.findByFolder_Id(folderId));
    }

    public List<DocumentDTO> findByDocumentTypeId(int typeId) {
        return documentMapper.toDTOList(documentRepository.findByDocumentType_IdType(typeId));
    }

    public List<DocumentDTO> findByStatus(String status) {
        return documentMapper.toDTOList(documentRepository.findByStatus(status));
    }

    public List<DocumentDTO> findByTitleContaining(String title) {
        return documentMapper.toDTOList(documentRepository.findByTitleContainingIgnoreCase(title));
    }

    public List<DocumentDTO> findByFileType(String fileType) {
        return documentMapper.toDTOList(documentRepository.findByFileType(fileType));
    }

    public long count() {
        return documentRepository.count();
    }
}
