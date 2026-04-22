package com.ProyectCAE.businessLayer.dto.documentTypeDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear un tipo de documento")
public class DocumentTypeCreateDTO {
    @Schema(description = "Nombre del tipo", example = "PDF", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Indica si es global", example = "true", required = true)
    @NotNull
    private Boolean isGlobal;

    @Schema(description = "Estado", example = "true", required = true)
    @NotNull
    private Boolean status;
}
