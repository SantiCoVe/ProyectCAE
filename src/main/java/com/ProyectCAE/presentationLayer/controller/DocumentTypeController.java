package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeCreateDTO;
import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeDTO;
import com.ProyectCAE.businessLayer.dto.documentTypeDTOs.DocumentTypeUpdateDTO;
import com.ProyectCAE.persistenceLayer.dao.DocumentTypeDAO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeDAO documentTypeDAO;

    public DocumentTypeController(DocumentTypeDAO documentTypeDAO) {
        this.documentTypeDAO = documentTypeDAO;
    }

    @GetMapping
    public List<DocumentTypeDTO> list() {
        return documentTypeDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> getById(@PathVariable Long id) {
        return documentTypeDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/global")
    public List<DocumentTypeDTO> listGlobal() {
        return documentTypeDAO.findGlobalTypes();
    }

    @PostMapping
    public ResponseEntity<DocumentTypeDTO> create(@RequestBody @Valid DocumentTypeCreateDTO createDTO) {
        if (documentTypeDAO.existsByName(createDTO.getName())) {
            return ResponseEntity.badRequest().build();
        }
        DocumentTypeDTO created = documentTypeDAO.save(createDTO);
        return ResponseEntity.created(URI.create("/api/document-types/" + created.getIdType())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> update(@PathVariable Long id, @RequestBody @Valid DocumentTypeUpdateDTO updateDTO) {
        return documentTypeDAO.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return documentTypeDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

