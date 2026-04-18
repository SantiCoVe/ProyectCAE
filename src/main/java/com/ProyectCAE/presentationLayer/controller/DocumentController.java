package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import com.ProyectCAE.businessLayer.service.DocumentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Upload document
    @PostMapping("/upload")
    public DocumentEntity uploadDocument(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile file,
            @RequestParam Long userId,
            @RequestParam Long folderId,
            @RequestParam Long documentTypeId
    ) {

        try {
            /*
            // save files in the system
            String filePath = "uploads/" + file.getOriginalFilename();

            java.nio.file.Files.copy(
                    file.getInputStream(),
                    java.nio.file.Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
            */

            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);

            // crear carpeta si no existe
            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }

            // ruta final del archivo
            String filePath = uploadDir + file.getOriginalFilename();

            // guardar archivo (sobrescribe si existe)
            java.nio.file.Files.copy(
                    file.getInputStream(),
                    java.nio.file.Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            // save in bd
            return documentService.createDocument(
                    title,
                    description,
                    filePath,
                    file.getContentType(),
                    file.getSize(),
                    userId,
                    folderId,
                    documentTypeId
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading file");
        }
    }

    // list for carpet
    @GetMapping("/folder/{folderId}")
    public List<DocumentEntity> getDocumentsByFolder(@PathVariable Long folderId) {
        return documentService.getDocumentsByFolder(folderId);
    }

    // delete document
    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }
}