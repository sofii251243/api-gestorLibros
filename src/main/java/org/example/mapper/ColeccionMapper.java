package org.example.mapper;

import org.example.dto.ColeccionDTO;
import org.example.model.Coleccion;
import org.springframework.stereotype.Component;

@Component
public class ColeccionMapper {
    public ColeccionDTO toDTO(Coleccion coleccion) {
        if (coleccion == null) {
            return null;
        }
        ColeccionDTO dto = new ColeccionDTO();
        dto.setId(coleccion.getId());
        dto.setUsuarioId(coleccion.getUsuarioId());
        dto.setNombre(coleccion.getNombre());
        dto.setFechaCreacion(coleccion.getFechaCreacion());
        return dto;
    }

    public Coleccion toEntity(ColeccionDTO dto) {
        if (dto == null) {
            return null;
        }
        Coleccion coleccion = new Coleccion();
        coleccion.setId(dto.getId());
        coleccion.setUsuarioId(dto.getUsuarioId());
        coleccion.setNombre(dto.getNombre());
        coleccion.setFechaCreacion(dto.getFechaCreacion());
        return coleccion;
    }
}

