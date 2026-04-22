package com.ProyectCAE.businessLayer.dto.documentDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un documento")
public class DocumentUpdateDTO {
    @Schema(description = "Título", example = "Contrato 001 (actualizado)")
    private String title;

    @Schema(description = "Descripción", example = "Descripción actualizada")
    private String description;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;

    @Schema(description = "Observación", example = "Observación actualizada")
    private String observation;

    @Schema(description = "ID de carpeta", example = "10")
    private Long folderId;

    @Schema(description = "ID de tipo de documento", example = "3")
    private Long documentTypeId;
}
