package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Long> {

    List<DocumentTypeEntity> findByIsGlobalTrue();

    List<DocumentTypeEntity> findByCreatedById(Long userId);

}