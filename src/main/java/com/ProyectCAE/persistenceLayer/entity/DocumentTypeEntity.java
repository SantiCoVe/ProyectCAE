package com.ProyecyCAE.persistenceLayer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;

    private String name;
    private Boolean isGlobal;
    private Boolean status;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "type")
    private List<DocumentEntity> documents;
}