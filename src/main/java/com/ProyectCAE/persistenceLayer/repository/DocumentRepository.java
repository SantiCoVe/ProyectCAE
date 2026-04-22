/*
package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    List<DocumentEntity> findByFolderId(Long folderId);

    List<DocumentEntity> findByCreatedById(Long userId);

    List<DocumentEntity> findByDocumentTypeId(Long documentTypeId);

} */

package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    List<DocumentEntity> findByFolder_Id(Integer folderId);

    List<DocumentEntity> findByDocumentType_IdType(Integer typeId);

    List<DocumentEntity> findByStatus(String status);

    List<DocumentEntity> findByTitleContainingIgnoreCase(String title);

    List<DocumentEntity> findByFileType(String fileType);

    List<DocumentEntity> findByFolder_IdAndStatus(Integer folderId, String status);
}
