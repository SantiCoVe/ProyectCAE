package com.ProyectCAE.businessLayer.service;

import com.ProyectCAE.persistenceLayer.entity.*;
import com.ProyectCAE.persistenceLayer.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            UserRepository userRepository,
            FolderRepository folderRepository,
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    // 🔥 CREATE DOCUMENT
    @Override
    public DocumentEntity createDocument(
            String title,
            String description,
            String filePath,
            String fileType,
            Long size,
            Boolean active,
            Long userId,
            Long folderId,
            Long documentTypeId
    ) {

        // validate user
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // validate carpet
        FolderEntity folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // validate documentType
        DocumentTypeEntity documentType = documentTypeRepository.findById(documentTypeId)
                .orElseThrow(() -> new RuntimeException("Document type not found"));

        // build document
        DocumentEntity document = new DocumentEntity();
        document.setTitle(title);
        document.setDescription(description);
        document.setFilePath(filePath);
        document.setFileType(fileType);
        document.setActive(active != null ? active : true);
        document.setFileSize(size);

        document.setCreatedBy(user);
        document.setFolder(folder);
        document.setDocumentType(documentType);

        // save
        return documentRepository.save(document);
    }

    // 📂 GET DOCUMENTS BY FOLDER
    @Override
    public List<DocumentEntity> getDocumentsByFolder(Long folderId) {
        return documentRepository.findByFolder_Id(folderId);
    }

    // delete document
    @Override
    public void deleteDocument(Long documentId) {

        DocumentEntity document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // aquí luego implementacion de borrar archivo físico
        // Files.delete(...)

        documentRepository.delete(document);
    }
}
