package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.NoteDTO;
import org.example.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.NOTES_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO createdNote = noteService.createNote(noteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping(RoutesConfig.NOTES_BY_ID_REL)
    public ResponseEntity<NoteDTO> updateNote(@PathVariable Long id, @Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO updatedNote = noteService.updateNote(id, noteDTO);
        return ResponseEntity.ok(updatedNote);
    }

    @GetMapping(RoutesConfig.NOTES_BY_ID_REL)
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.NOTES_BY_LECTURA_REL)
    public ResponseEntity<List<NoteDTO>> getNotesByLecturaId(@PathVariable Long lecturaId) {
        List<NoteDTO> notes = noteService.getNotesByLecturaId(lecturaId);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping(RoutesConfig.NOTES_BY_ID_REL)
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}


