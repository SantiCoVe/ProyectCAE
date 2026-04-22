package com.ProyectCAE.persistenceLayer.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document_type")

public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;

    private String name;
    private Boolean isGlobal; // true = defaultSystem document types, false = user custom document type
    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // Documents that use this type
    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY)
    private List<DocumentEntity> documents;

    // Automatic timestamp
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}
