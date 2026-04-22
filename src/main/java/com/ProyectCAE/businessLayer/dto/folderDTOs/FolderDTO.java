package com.ProyectCAE.businessLayer.dto.folderDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información completa de una carpeta")
public class FolderDTO {
    @Schema(description = "ID único de la carpeta", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre de la carpeta", example = "Contratos")
    private String name;

    @Schema(description = "Descripción", example = "Carpeta para contratos 2026")
    private String description;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedDate;

    @Schema(description = "ID del usuario dueño", example = "1")
    private Long userId;

    @Schema(description = "Nombre(s) del usuario dueño", example = "Carlos")
    private String userNames;

    @Schema(description = "Cantidad de documentos", example = "5", accessMode = Schema.AccessMode.READ_ONLY)
    private Long documentCount;
}
