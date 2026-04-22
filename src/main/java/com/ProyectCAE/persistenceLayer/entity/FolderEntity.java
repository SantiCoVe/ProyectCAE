package com.ProyectCAE.persistenceLayer.entity;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folder")

public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folder")
    private Integer id;

    private String name;
    private String description;
    private String status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    // User relationship (carpet owner)
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity createdBy;

    // Document relationship
    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    private List<DocumentEntity> documents;

    // Automatic Timestamps
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
