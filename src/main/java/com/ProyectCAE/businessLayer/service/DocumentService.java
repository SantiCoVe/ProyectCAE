package com.ProyectCAE.businessLayer.service;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;

import java.util.List;

public interface DocumentService {

    DocumentEntity createDocument(
            String title,
            String description,
            String filePath,
            String fileType,
            Long size,
            Boolean active,
            Long userId,
            Long folderId,
            Long documentTypeId
    );

    List<DocumentEntity> getDocumentsByFolder(Long folderId);

    void deleteDocument(Long documentId);
}