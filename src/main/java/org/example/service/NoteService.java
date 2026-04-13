package org.example.service;

import org.example.dto.NoteDTO;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);
    NoteDTO updateNote(Long id, NoteDTO noteDTO);
    Optional<NoteDTO> getNoteById(Long id);
    List<NoteDTO> getNotesByLecturaId(Long lecturaId);
    void deleteNote(Long id);
}

