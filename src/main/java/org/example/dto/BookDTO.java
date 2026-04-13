package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotNull(message = "El ID del usuario es requerido")
    private Long usuarioId;

    @NotBlank(message = "La ruta del archivo es requerida")
    private String rutaArchivo;

    @NotBlank(message = "El nombre del archivo es requerido")
    private String nombreArchivo;

    @NotBlank(message = "El título es requerido")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    private String titulo;

    @NotBlank(message = "El formato es requerido")
    private String formato;

    private String autor;

    private Long serieId;

    @DecimalMin(value = "0.0", message = "El progreso no puede ser negativo")
    @DecimalMax(value = "100.0", message = "El progreso no puede ser mayor a 100")
    private Float progreso;

    @Pattern(regexp = "^(PENDIENTE|LEYENDO|COMPLETADO|PAUSADO)$", message = "El estado debe ser uno de: PENDIENTE, LEYENDO, COMPLETADO, PAUSADO")
    private String estado;

    private String coverPath;

    private Long fechaCreacion;
}

