package org.example.service;

import org.example.dto.LecturaColeccionDTO;
import java.util.List;

public interface LecturaColeccionService {
    LecturaColeccionDTO addBookToColeccion(LecturaColeccionDTO lecturaColeccionDTO);
    void removeBookFromColeccion(Long lecturaId, Long coleccionId);
    List<LecturaColeccionDTO> getBooksByColeccion(Long coleccionId);
    List<LecturaColeccionDTO> getColeccionesByBook(Long lecturaId);
}

