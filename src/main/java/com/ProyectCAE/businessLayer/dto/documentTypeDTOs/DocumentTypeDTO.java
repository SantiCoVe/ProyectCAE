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
    private Integer idType;

    @Schema(description = "Nombre del tipo", example = "PDF")
    private String name;

    @Schema(description = "Indica si es un tipo global del sistema", example = "true")
    private Boolean isGlobal;

    @Schema(description = "Estado", example = "true")
    private Boolean status;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updateDate;
}
