package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {

    List<FolderEntity> findByCreatedBy_Id(Integer userId);

    List<FolderEntity> findByStatus(String status);

    List<FolderEntity> findByNameContainingIgnoreCase(String name);

    List<FolderEntity> findByCreatedBy_IdAndStatus(Integer userId, String status);

    @Query("select f from FolderEntity f where f.documents is empty")
    List<FolderEntity> findEmptyFolders();

    @Query("select count(d) from DocumentEntity d where d.folder.id = :folderId")
    Long countDocumentsByFolderId(@Param("folderId") Integer folderId);
}
