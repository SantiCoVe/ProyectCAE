package com.ProyectCAE.presentationLayer.controller;

import com.ProyectCAE.businessLayer.dto.userDTOs.UserCreateDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserUpdateDTO;
import com.ProyectCAE.persistenceLayer.dao.UserDAO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<UserDTO> list() {
        return userDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO createDTO) {
        if (userDAO.existsByEmail(createDTO.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        UserDTO created = userDAO.save(createDTO);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO updateDTO) {
        return userDAO.update(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userDAO.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

