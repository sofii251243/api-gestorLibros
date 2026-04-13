package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDTO {
    private Long id;

    @NotNull(message = "El ID de la lectura es requerido")
    private Long lecturaId;

    @NotBlank(message = "El texto citado es requerido")
    private String textoCitado;

    private String referenciaPagina;

    private String comentario;

    private Long fechaCreacion;
}

