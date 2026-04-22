package com.ProyectCAE.businessLayer.dto.documentDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear un documento")
public class DocumentCreateDTO {
    @Schema(description = "Título", example = "Contrato 001", required = true)
    @NotBlank
    private String title;

    @Schema(description = "Descripción", example = "Contrato firmado")
    private String description;

    @Schema(description = "Ruta del archivo", example = "C:/uploads/contrato.pdf", required = true)
    @NotBlank
    private String filePath;

    @Schema(description = "Tipo MIME", example = "application/pdf")
    private String fileType;

    @Schema(description = "Tamaño en bytes", example = "12345")
    private Long fileSize;

    @Schema(description = "Estado activo/inactivo", example = "true", required = true)
    @NotNull
    private Boolean active;

    @Schema(description = "Observación", example = "Revisar firmas")
    private String observation;

    @Schema(description = "ID de carpeta", example = "10", required = true)
    @NotNull
    private Long folderId;

    @Schema(description = "ID de tipo de documento", example = "3", required = true)
    @NotNull
    private Long documentTypeId;

    @Schema(description = "ID del usuario creador", example = "1", required = true)
    @NotNull
    private Long createdById;
}
