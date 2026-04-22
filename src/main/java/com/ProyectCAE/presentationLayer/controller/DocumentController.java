package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentUpdateDTO;
import com.ProyectCAE.businessLayer.service.DocumentService;
import com.ProyectCAE.persistenceLayer.dao.DocumentDAO;
import com.ProyectCAE.persistenceLayer.mapper.DocumentMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentDAO documentDAO;
    private final DocumentMapper documentMapper;

    public DocumentController(DocumentService documentService, DocumentDAO documentDAO, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.documentDAO = documentDAO;
        this.documentMapper = documentMapper;
    }

    // Upload document
    @PostMapping("/upload")
    public DocumentDTO uploadDocument(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile file,
            @RequestParam Boolean active,
            @RequestParam Long userId,
            @RequestParam Long folderId,
            @RequestParam Long documentTypeId
    ) {

        try {
            /*
            // codigo para manejar guardado en bd
            // save files in the uploads carpet
            String filePath = "uploads/" + file.getOriginalFilename();

            java.nio.file.Files.copy(
                    file.getInputStream(),
                    java.nio.file.Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
            */

            // codigo para manejar guardado local (sin bd)
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);
            java.nio.file.Files.createDirectories(uploadPath);

            // ruta final del archivo
            String filePath = uploadDir + file.getOriginalFilename();

            // guardar archivo (sobrescribe si existe)
            java.nio.file.Files.copy(
                    file.getInputStream(),
                    java.nio.file.Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            // save in bd
            return documentMapper.toDTO(documentService.createDocument(
                    title,
                    description,
                    filePath,
                    file.getContentType(),
                    file.getSize(),
                    active,
                    userId,
                    folderId,
                    documentTypeId
            ));

        } catch (Exception e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    // list for carpet
    @GetMapping("/folder/{folderId}")
    public List<DocumentDTO> getDocumentsByFolder(@PathVariable Long folderId) {
        return documentMapper.toDTOList(documentService.getDocumentsByFolder(folderId));
    }

    @GetMapping
    public List<DocumentDTO> list() {
        return documentDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable long id) {
        return documentDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DocumentDTO> create(@RequestBody @Valid DocumentCreateDTO createDTO) {
        DocumentDTO created = documentDAO.save(createDTO);
        return ResponseEntity.created(URI.create("/api/documents/" + created.getIdDocument())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable long id, @RequestBody @Valid DocumentUpdateDTO updateDTO) {
        return documentDAO.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // delete document (soft/hard depending on entity flag)
    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }
}
