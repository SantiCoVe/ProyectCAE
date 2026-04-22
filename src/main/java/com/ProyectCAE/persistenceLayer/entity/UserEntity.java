package com.ProyectCAE.persistenceLayer.entity;

import com.ProyectCAE.persistenceLayer.entity.FolderEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String password;
    private String phone;
    private String userRole;
    private Boolean status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // Automatic timestamps
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    // relationship with folders
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<FolderEntity> folders;

    // relationship with document
//    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
//    private List<FolderEntity> folders;
}
