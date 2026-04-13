package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;

    @NotNull(message = "El ID de la lectura es requerido")
    private Long lecturaId;

    @NotBlank(message = "El contenido es requerido")
    private String contenido;

    private String referenciaPagina;

    private Long fechaCreacion;
}

