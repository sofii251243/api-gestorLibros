package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaColeccionDTO {
    @NotNull(message = "El ID de la lectura es requerido")
    private Long lecturaId;

    @NotNull(message = "El ID de la colección es requerido")
    private Long coleccionId;

    private Long fechaAgregado;
}

