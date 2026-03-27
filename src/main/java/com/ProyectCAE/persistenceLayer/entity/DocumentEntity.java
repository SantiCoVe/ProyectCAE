package com.ProyectoCAE.persistenceLayer.entity;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentEntity {

    @id
    @GenerationType(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String documentType;

    @Column(name = "created_at");
    private LocalDateTime createdAt;

    @Column(name = "updated_at");
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "UserEntity", fetch = FetchType.LAZY)
    private List<DocumentEntity> documents;
}