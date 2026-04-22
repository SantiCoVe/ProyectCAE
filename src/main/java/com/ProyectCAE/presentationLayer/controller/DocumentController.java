package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentDTO;
import com.ProyectCAE.businessLayer.dto.documentDTOs.DocumentUpdateDTO;
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

    private final DocumentDAO documentDAO;
    private final DocumentMapper documentMapper;

    public DocumentController(DocumentDAO documentDAO, DocumentMapper documentMapper) {
        this.documentDAO = documentDAO;
        this.documentMapper = documentMapper;
    }

    // Upload document
    @PostMapping("/upload")
    public DocumentDTO uploadDocument(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer userId,
            @RequestParam Integer folderId,
            @RequestParam Integer documentTypeId
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

            String resolvedStatus = status;
            if (resolvedStatus == null || resolvedStatus.isBlank()) {
                resolvedStatus = (active != null && !active) ? "INACTIVE" : "ACTIVE";
            }

            DocumentCreateDTO createDTO = new DocumentCreateDTO(
                    title,
                    description,
                    filePath,
                    file.getContentType(),
                    resolvedStatus,
                    null,
                    folderId,
                    documentTypeId
            );

            return documentDAO.save(createDTO);

        } catch (Exception e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    // list for carpet
    @GetMapping("/folder/{folderId}")
    public List<DocumentDTO> getDocumentsByFolder(@PathVariable Integer folderId) {
        return documentDAO.findByFolderId(folderId);
    }

    @GetMapping
    public List<DocumentDTO> list() {
        return documentDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable Integer id) {
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
    public ResponseEntity<DocumentDTO> update(@PathVariable Integer id, @RequestBody @Valid DocumentUpdateDTO updateDTO) {
        return documentDAO.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // delete document (soft/hard depending on entity flag)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer id) {
        return documentDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
