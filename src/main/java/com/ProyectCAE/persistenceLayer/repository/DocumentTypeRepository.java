package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentEntity, Long> {



}