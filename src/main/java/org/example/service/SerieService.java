package org.example.service;

import org.example.dto.SerieDTO;
import java.util.List;
import java.util.Optional;

public interface SerieService {
    SerieDTO createSerie(SerieDTO serieDTO);
    SerieDTO updateSerie(Long id, SerieDTO serieDTO);
    Optional<SerieDTO> getSerieById(Long id);
    List<SerieDTO> getSeriesByUsuarioId(Long usuarioId);
    void deleteSerie(Long id);
}

