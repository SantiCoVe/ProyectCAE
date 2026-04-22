package com.ProyectCAE.businessLayer.dto.documentTypeDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar un tipo de documento")
public class DocumentTypeUpdateDTO {
    @Schema(description = "Nombre del tipo", example = "PDF (actualizado)")
    private String name;

    @Schema(description = "Indica si es global", example = "true")
    private Boolean isGlobal;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;
}
