package com.ProyectCAE.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @id
    @GenerateValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String lastNames;
    private String email;
    private String password;
    private String phone;
    private String userRol;
    private String status;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<DocumentEntity> documents;
}
