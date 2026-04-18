package com.ProyectCAE.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.validation.Schema;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa del usuario")
public class UserDTO {

    @Schema(description = "ID único del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUsuario;

    @Schema(description = "Nombre del usuario", example = "Carlos")
    private String name;

    @Schema(description = "Apellido del usuario", example = "Ramírez")
    private String lastName;

    @Schema(description = "Correo electrónico", example = "carlos@empresa.com")
    private String email;

    @Schema(description = "Teléfono de contacto", example = "+57-300-1234567")
    private String phone;

    @Schema(description = "Rol del usuario", example = "ADMIN")
    private String userRole;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean status;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;

    @Schema(description = "Fecha de última actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updateDate;
}
