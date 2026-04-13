package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColeccionDTO {
    private Long id;

    @NotNull(message = "El ID del usuario es requerido")
    private Long usuarioId;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    private Long fechaCreacion;
}

