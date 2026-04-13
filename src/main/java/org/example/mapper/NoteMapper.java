package org.example.mapper;

import org.example.dto.NoteDTO;
import org.example.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDTO toDTO(Note note) {
        if (note == null) {
            return null;
        }
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setLecturaId(note.getLecturaId());
        dto.setContenido(note.getContenido());
        dto.setReferenciaPagina(note.getReferenciaPagina());
        dto.setFechaCreacion(note.getFechaCreacion());
        return dto;
    }

    public Note toEntity(NoteDTO dto) {
        if (dto == null) {
            return null;
        }
        Note note = new Note();
        note.setId(dto.getId());
        note.setLecturaId(dto.getLecturaId());
        note.setContenido(dto.getContenido());
        note.setReferenciaPagina(dto.getReferenciaPagina());
        note.setFechaCreacion(dto.getFechaCreacion());
        return note;
    }
}

