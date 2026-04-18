package com.ProyectCAE.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.validation.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Datos para actualizar un usuario existente")
public class UserUpdateDTO {

    @Schema(description = "Nombre del usuario", example = "Carlos Alberto")
    private String name;

    @Schema(description = "Apellido del usuario", example = "Ramírez López")
    private String lastName;

    @Schema(description = "Teléfono de contacto", example = "+57-310-9876543")
    private String phone;

    @Schema(description = "Rol del usuario", example = "ADMIN")
    private String userRole;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean status;
}
