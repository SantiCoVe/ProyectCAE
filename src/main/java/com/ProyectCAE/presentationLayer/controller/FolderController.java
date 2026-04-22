package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderCreateDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderUpdateDTO;
import com.ProyectCAE.persistenceLayer.dao.FolderDAO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderDAO folderDAO;

    public FolderController(FolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }

    @GetMapping
    public List<FolderDTO> list() {
        return folderDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderDTO> getById(@PathVariable Integer id) {
        return folderDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public List<FolderDTO> listByUser(@PathVariable Integer userId) {
        return folderDAO.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<FolderDTO> create(@RequestBody @Valid FolderCreateDTO createDTO) {
        FolderDTO created = folderDAO.save(createDTO);
        return ResponseEntity.created(URI.create("/api/folders/" + created.getIdFolder())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FolderDTO> update(@PathVariable Integer id, @RequestBody @Valid FolderUpdateDTO updateDTO) {
        return folderDAO.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return folderDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
