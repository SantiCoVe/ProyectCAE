package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Long> {

    List<DocumentTypeEntity> findByIsGlobalTrue();

    List<DocumentTypeEntity> findByIsGlobal(Boolean isGlobal);

    List<DocumentTypeEntity> findByCreatedBy_Id(Long userId);

    boolean existsByNameIgnoreCase(String name);

    Optional<DocumentTypeEntity> findByNameIgnoreCase(String name);

    List<DocumentTypeEntity> findByActive(Boolean active);

    List<DocumentTypeEntity> findByActiveAndIsGlobal(Boolean active, Boolean isGlobal);

    @Query("select distinct dt from DocumentTypeEntity dt join dt.documents d")
    List<DocumentTypeEntity> findTypesInUse();
}
