package com.ProyectCAE.businessLayer.dto.folderDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una carpeta")
public class FolderCreateDTO {
    @Schema(description = "Nombre de la carpeta", example = "Contratos", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Descripción", example = "Carpeta para contratos 2026")
    private String description;

    @Schema(description = "Estado activo/inactivo", example = "true", required = true)
    @NotNull
    private Boolean active;

    @Schema(description = "ID del usuario dueño", example = "1", required = true)
    @NotNull
    private Long userId;
}
