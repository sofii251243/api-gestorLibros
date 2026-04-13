package org.example.mapper;

import org.example.dto.SerieDTO;
import org.example.model.Serie;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper {
    public SerieDTO toDTO(Serie serie) {
        if (serie == null) {
            return null;
        }
        SerieDTO dto = new SerieDTO();
        dto.setId(serie.getId());
        dto.setUsuarioId(serie.getUsuarioId());
        dto.setNombre(serie.getNombre());
        dto.setFechaCreacion(serie.getFechaCreacion());
        return dto;
    }

    public Serie toEntity(SerieDTO dto) {
        if (dto == null) {
            return null;
        }
        Serie serie = new Serie();
        serie.setId(dto.getId());
        serie.setUsuarioId(dto.getUsuarioId());
        serie.setNombre(dto.getNombre());
        serie.setFechaCreacion(dto.getFechaCreacion());
        return serie;
    }
}

