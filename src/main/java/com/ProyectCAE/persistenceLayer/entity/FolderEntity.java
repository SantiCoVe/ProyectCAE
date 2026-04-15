package com.ProyectCAE.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "folders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFolder;

    private String name;
    private String description;
    private String status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "folder")
    private List<DocumentEntity> documents;
}