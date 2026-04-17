package com.ProyectCAE.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsCOnstructor
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
            maxLenght = 150
    )
    private String lastName;
}