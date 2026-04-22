package com.ProyectCAE.persistenceLayer.dao;

import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderCreateDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderDTO;
import com.ProyectCAE.businessLayer.dto.folderDTOs.FolderUpdateDTO;
import com.ProyectCAE.persistenceLayer.mapper.FolderMapper;
import com.ProyectCAE.persistenceLayer.repository.FolderRepository;
import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO para operaciones de persistencia de carpetas.
 * Incluye datos denormalizados del usuario (userId, userName) en el DTO de respuesta.
 */
@Repository
@RequiredArgsConstructor
public class FolderDAO {

    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    public FolderDTO save(FolderCreateDTO createDTO) {
        FolderEntity entity = folderMapper.toEntity(createDTO);
        FolderEntity savedEntity = folderRepository.save(entity);
        return folderMapper.toDTO(savedEntity);
    }

    public Optional<FolderDTO> findById(Integer id) {
        return folderRepository.findById(id)
                .map(folderMapper::toDTO);
    }

    public List<FolderDTO> findAll() {
        return folderMapper.toDTOList(folderRepository.findAll());
    }

    public Optional<FolderDTO> update(Integer id, FolderUpdateDTO updateDTO) {
        return folderRepository.findById(id)
                .map(existing -> {
                    folderMapper.updateEntityFromDTO(updateDTO, existing);
                    return folderMapper.toDTO(folderRepository.save(existing));
                });
    }

    public boolean deleteById(Integer id) {
        if (folderRepository.existsById(id)) {
            folderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FolderDTO> findByUserId(Integer userId) {
        return folderMapper.toDTOList(folderRepository.findByCreatedBy_Id(userId));
    }

    public List<FolderDTO> findByStatus(String status) {
        return folderMapper.toDTOList(folderRepository.findByStatus(status));
    }

    public List<FolderDTO> findByNameContaining(String name) {
        return folderMapper.toDTOList(folderRepository.findByNameContainingIgnoreCase(name));
    }

    public List<FolderDTO> findByUserIdAndStatus(Integer userId, String status) {
        return folderMapper.toDTOList(folderRepository.findByCreatedBy_IdAndStatus(userId, status));
    }

    public List<FolderDTO> findEmptyFolders() {
        return folderMapper.toDTOList(folderRepository.findEmptyFolders());
    }

    public Long countDocumentsByFolderId(Integer folderId) {
        return folderRepository.countDocumentsByFolderId(folderId);
    }

    public long count() {
        return folderRepository.count();
    }
}
