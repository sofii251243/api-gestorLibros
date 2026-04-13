package org.example.mapper;

import org.example.dto.LecturaColeccionDTO;
import org.example.model.LecturaColeccion;
import org.springframework.stereotype.Component;

@Component
public class LecturaColeccionMapper {
    public LecturaColeccionDTO toDTO(LecturaColeccion lecturaColeccion) {
        if (lecturaColeccion == null) {
            return null;
        }
        LecturaColeccionDTO dto = new LecturaColeccionDTO();
        dto.setLecturaId(lecturaColeccion.getId().getLecturaId());
        dto.setColeccionId(lecturaColeccion.getId().getColeccionId());
        dto.setFechaAgregado(lecturaColeccion.getFechaAgregado());
        return dto;
    }

    public LecturaColeccion toEntity(LecturaColeccionDTO dto) {
        if (dto == null) {
            return null;
        }
        LecturaColeccion lecturaColeccion = new LecturaColeccion();
        lecturaColeccion.setId(new org.example.model.LecturaColeccionId(dto.getLecturaId(), dto.getColeccionId()));
        lecturaColeccion.setFechaAgregado(dto.getFechaAgregado());
        return lecturaColeccion;
    }
}

