package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    //Busqueda de usuarios por folder
    List<UserEntity> findUsersWithFolders();

    //Busqueda de todos los usuarios por rol
    List<UserEntity> findByUserRole(String role);

    //Busqueda de todos los usuarios por estado
    List<UserEntity> findByStatus(Boolean status);

    //Busqueda de usuario por nombre
    List<UserEntity> findByNameContainingIgnoreCase(String name);

    //Validadcion de usuario por email
    boolean existsByEmail(String email);
}