package com.ProyectCAE.businessLayer.dto.documentDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de un documento")
public class DocumentDTO {
    @Schema(description = "ID único del documento", example = "100", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idDocument;

    @Schema(description = "Título", example = "Contrato 001")
    private String title;

    @Schema(description = "Descripción", example = "Contrato firmado")
    private String description;

    @Schema(description = "Ruta del archivo", example = "C:/uploads/contrato.pdf")
    private String filePath;

    @Schema(description = "Tipo MIME", example = "application/pdf")
    private String fileType;

    @Schema(description = "Tamaño en bytes", example = "12345")
    private Long fileSize;

    @Schema(description = "Estado activo/inactivo", example = "true")
    private Boolean active;

    @Schema(description = "Observación", example = "Revisar firmas")
    private String observation;

    @Schema(description = "Fecha de creación", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;

    @Schema(description = "Fecha de actualización", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedDate;

    @Schema(description = "ID de carpeta", example = "10")
    private Long folderId;

    @Schema(description = "Nombre de carpeta", example = "Contratos")
    private String folderName;

    @Schema(description = "ID de tipo de documento", example = "3")
    private Long documentTypeId;

    @Schema(description = "Nombre de tipo de documento", example = "PDF")
    private String documentTypeName;

    @Schema(description = "ID del usuario creador", example = "1")
    private Long createdById;
}
