package com.ProyectCAE.persistenceLayer.dao;

import com.ProyectCAE.businessLayer.dto.userDTOs.UserCreateDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserDTO;
import com.ProyectCAE.businessLayer.dto.userDTOs.UserUpdateDTO;
import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import com.ProyectCAE.persistenceLayer.mapper.UserMapper;
import com.ProyectCAE.persistenceLayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO para operaciones de persistencia de usuarios.
 * Abstrae la lógica de acceso a datos del Service.
 * El Service solo trabaja con DTOs, nunca con entidades.
 */
@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO save(UserCreateDTO createDTO) {
        UserEntity entity = userMapper.toEntity(createDTO);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDTO(savedEntity);
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    public List<UserDTO> findAll() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public Optional<UserDTO> update(Long id, UserUpdateDTO updateDTO) {
        return userRepository.findById(id)
                .map(existing -> {
                    userMapper.updateEntityFromDTO(updateDTO, existing);
                    return userMapper.toDTO(userRepository.save(existing));
                });
    }

    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO);
    }

    public List<UserDTO> findByNameContaining(String name) {
        return userMapper.toDTOList(userRepository.findByNamesContainingIgnoreCase(name));
    }

    public List<UserDTO> findByStatus(Boolean status) {
        return userMapper.toDTOList(userRepository.findByStatus(status));
    }

    public List<UserDTO> findByRole(String role) {
        return userMapper.toDTOList(userRepository.findByUserRole(role));
    }

    public List<UserDTO> findUsersWithFolders() {
        return userMapper.toDTOList(userRepository.findUsersWithFolders());
    }

    public long count() {
        return userRepository.count();
    }
}
