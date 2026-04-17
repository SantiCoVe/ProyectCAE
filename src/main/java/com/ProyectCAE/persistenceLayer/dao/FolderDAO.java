package com.ProyectCAE.persistenceLayer.dao;

import com.tuempresa.gestor.businessLayer.dto.folder.FolderCreateDTO;
import com.tuempresa.gestor.businessLayer.dto.folder.FolderDTO;
import com.tuempresa.gestor.businessLayer.dto.folder.FolderUpdateDTO;
import com.tuempresa.gestor.persistenceLayer.entity.FolderEntity;
import com.tuempresa.gestor.persistenceLayer.mapper.FolderMapper;
import com.tuempresa.gestor.persistenceLayer.repository.FolderRepository;
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

    public Optional<FolderDTO> findById(Long id) {
        return folderRepository.findById(id)
                .map(folderMapper::toDTO);
    }

    public List<FolderDTO> findAll() {
        return folderMapper.toDTOList(folderRepository.findAll());
    }

    public Optional<FolderDTO> update(Long id, FolderUpdateDTO updateDTO) {
        return folderRepository.findById(id)
                .map(existing -> {
                    folderMapper.updateEntityFromDTO(updateDTO, existing);
                    return folderMapper.toDTO(folderRepository.save(existing));
                });
    }

    public boolean deleteById(Long id) {
        if (folderRepository.existsById(id)) {
            folderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FolderDTO> findByUserId(Long userId) {
        return folderMapper.toDTOList(folderRepository.findByUserIdUsuario(userId));
    }

    public List<FolderDTO> findByActive(String active) {
        return folderMapper.toDTOList(folderRepository.findByActive(active));
    }

    public List<FolderDTO> findByNameContaining(String name) {
        return folderMapper.toDTOList(folderRepository.findByNameContainingIgnoreCase(name));
    }

    public List<FolderDTO> findByUserIdAndActive(Long userId, String active) {
        return folderMapper.toDTOList(folderRepository.findByUserIdUsuarioAndActive(userId, active));
    }

    public List<FolderDTO> findEmptyFolders() {
        return folderMapper.toDTOList(folderRepository.findEmptyFolders());
    }

    public Long countDocumentsByFolderId(Long folderId) {
        return folderRepository.countDocumentsByFolderId(folderId);
    }

    public long count() {
        return folderRepository.count();
    }
}
