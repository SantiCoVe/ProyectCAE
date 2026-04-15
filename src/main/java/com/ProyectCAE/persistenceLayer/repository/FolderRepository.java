package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface FolderRepository extends JpaRepository<FolderEntity, Long> {

    List<FolderEntity> findByOwnerId(Long userId);

}