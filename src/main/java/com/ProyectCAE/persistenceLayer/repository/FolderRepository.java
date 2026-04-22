package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

    List<FolderEntity> findByCreatedBy_Id(Long userId);

    List<FolderEntity> findByActive(Boolean active);

    List<FolderEntity> findByNameContainingIgnoreCase(String name);

    List<FolderEntity> findByCreatedBy_IdAndActive(Long userId, Boolean active);

    @Query("select f from FolderEntity f where f.documents is empty")
    List<FolderEntity> findEmptyFolders();

    @Query("select count(d) from DocumentEntity d where d.folder.id = :folderId")
    Long countDocumentsByFolderId(@Param("folderId") Long folderId);
}
