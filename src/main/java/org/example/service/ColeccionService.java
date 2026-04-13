package org.example.service;

import org.example.dto.ColeccionDTO;
import java.util.List;
import java.util.Optional;

public interface ColeccionService {
    ColeccionDTO createColeccion(ColeccionDTO coleccionDTO);
    ColeccionDTO updateColeccion(Long id, ColeccionDTO coleccionDTO);
    Optional<ColeccionDTO> getColeccionById(Long id);
    List<ColeccionDTO> getColeccionesByUsuarioId(Long usuarioId);
    void deleteColeccion(Long id);
}

