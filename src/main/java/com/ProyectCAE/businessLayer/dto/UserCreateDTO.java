package com.ProyectCAE.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

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
    private String name;

    @Schema(
            description = "Apellidos completos del usuario",
            example = "Vanegas Pinilla",
            required = true,
            maxLength = 150
    )
    private String lastName;
}