package com.ProyectCAE.businessLayer.dto.folderDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar una carpeta")
public class FolderUpdateDTO {
    @Schema(description = "Nombre de la carpeta", example = "Contratos (actualizado)")
    private String name;

    @Schema(description = "Descripción", example = "Descripción actualizada")
    private String description;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;
}
