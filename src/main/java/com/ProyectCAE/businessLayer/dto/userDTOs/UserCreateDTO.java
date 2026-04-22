package com.ProyectCAE.businessLayer.dto.userDTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear un nuevo usuario en el aplicativo")

public class UserCreateDTO {
    @Schema(
            description = "Nombre(s) completo(s) del usuario",
            example = "Mariana, Mariana Gonzalez",
            required = true,
            maxLength = 100
    )
    @NotBlank
    private String names;

    @Schema(
            description = "Apellidos completos del usuario",
            example = "Vanegas Pinilla",
            required = true,
            maxLength = 150
    )
    @NotBlank
    private String lastNames;

    @Schema(description = "Correo electrónico", example = "mariana@empresa.com", required = true)
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Contraseña", example = "********", required = true)
    @NotBlank
    private String password;

    @Schema(description = "Teléfono de contacto", example = "+57-300-1234567")
    private String phone;

    @Schema(description = "Rol del usuario", example = "ADMIN")
    private String userRole;

    @Schema(description = "Estado activo/inactivo", example = "true")
    @NotNull
    private Boolean status;
}
