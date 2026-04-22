package com.ProyectCAE.persistenceLayer.repository;

import com.ProyectCAE.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    //Busqueda de usuarios por folder
    @Query("select distinct u from UserEntity u left join fetch u.folders")
    List<UserEntity> findUsersWithFolders();

    //Busqueda de todos los usuarios por rol
    List<UserEntity> findByUserRole(String userRole);

    //Busqueda de todos los usuarios por estado
    List<UserEntity> findByStatus(Boolean status);

    //Busqueda de usuario por nombre
    List<UserEntity> findByNameContainingIgnoreCase(String name);

    //Validadcion de usuario por email
    boolean existsByEmail(String email);
}
