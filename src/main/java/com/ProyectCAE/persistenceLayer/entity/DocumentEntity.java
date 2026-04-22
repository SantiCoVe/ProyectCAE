package com.ProyectCAE.persistenceLayer.entity;

import com.ProyectCAE.persistenceLayer.entity.FolderEntity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")

public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Integer idDocument;

    private String title;
    private String description;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    private String status;
    private String observation;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // Automatic Timestamps
    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "id_folder")
    private FolderEntity folder;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private DocumentTypeEntity documentType;

}
