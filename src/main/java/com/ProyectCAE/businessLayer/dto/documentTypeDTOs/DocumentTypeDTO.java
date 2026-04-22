package com.ProyectCAE.businessLayer.dto.documentTypeDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de un tipo de documento")
public class DocumentTypeDTO {
    @Schema(description = "ID único del tipo", example = "3", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idType;

    @Schema(description = "Nombre del tipo", example = "PDF")
    private String name;

    @Schema(description = "Indica si es un tipo global del sistema", example = "true")
    private Boolean isGlobal;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @Schema(description = "ID del usuario creador", example = "1")
    private Long createdById;

    @Schema(description = "Nombre(s) del usuario creador", example = "Carlos")
    private String createdByNames;
}
