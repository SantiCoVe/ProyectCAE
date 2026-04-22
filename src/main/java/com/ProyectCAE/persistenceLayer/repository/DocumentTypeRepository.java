package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {

    List<DocumentTypeEntity> findByIsGlobalTrue();

    List<DocumentTypeEntity> findByIsGlobal(Boolean isGlobal);

    boolean existsByNameIgnoreCase(String name);

    Optional<DocumentTypeEntity> findByNameIgnoreCase(String name);

    List<DocumentTypeEntity> findByStatus(Boolean status);

    List<DocumentTypeEntity> findByStatusAndIsGlobal(Boolean status, Boolean isGlobal);

    @Query("select distinct dt from DocumentTypeEntity dt join dt.documents d")
    List<DocumentTypeEntity> findTypesInUse();
}
