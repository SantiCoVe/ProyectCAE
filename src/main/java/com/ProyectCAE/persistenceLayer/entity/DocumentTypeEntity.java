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
@Table(name = "document_types")

public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;

    private String name;
    private Boolean isGlobal; // true = defaultSystem document types, false = user custom document type
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // User relationship (who created the document type)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    // Document relationship
    @ManyToOne
    private DocumentTypeEntity documentType;

    // Automatic timestamp
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}