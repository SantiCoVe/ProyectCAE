package com.ProyectCAE.businessLayer.dto.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Datos para actualizar un usuario existente")
public class UserUpdateDTO {

    @Schema(description = "Nombre(s) del usuario", example = "Carlos Alberto")
    private String name;

    @Schema(description = "Apellido(s) del usuario", example = "Ramírez López")
    private String lastName;

    @Schema(description = "Correo electrónico", example = "carlos@empresa.com")
    @Email
    private String email;

    @Schema(description = "Contraseña", example = "********")
    private String password;

    @Schema(description = "Teléfono de contacto", example = "+57-310-9876543")
    private String phone;

    @Schema(description = "Rol del usuario", example = "ADMIN")
    private String userRole;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean status;
}
