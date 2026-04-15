package com.ProyectCAE.persistenceLayer.repository;

import com.proyectCAE.persistenceLayer.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    List<DocumentEntity> findByFolderId(Long folderId);

    List<DocumentEntity> findByCreatedById(Long userId);

    List<DocumentEntity> findByDocumentTypeId(Long documentTypeId);

}