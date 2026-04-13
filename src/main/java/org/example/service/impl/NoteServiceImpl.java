package org.example.service.impl;

import org.example.dto.NoteDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.NoteMapper;
import org.example.model.Note;
import org.example.repository.BookRepository;
import org.example.repository.NoteRepository;
import org.example.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        if (!bookRepository.existsById(noteDTO.getLecturaId())) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + noteDTO.getLecturaId());
        }
        Note note = noteMapper.toEntity(noteDTO);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDTO(savedNote);
    }

    @Override
    public NoteDTO updateNote(Long id, NoteDTO noteDTO) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con ID: " + id));

        note.setContenido(noteDTO.getContenido());
        note.setReferenciaPagina(noteDTO.getReferenciaPagina());

        Note updatedNote = noteRepository.save(note);
        return noteMapper.toDTO(updatedNote);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteDTO> getNoteById(Long id) {
        return noteRepository.findById(id).map(noteMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteDTO> getNotesByLecturaId(Long lecturaId) {
        if (!bookRepository.existsById(lecturaId)) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + lecturaId);
        }
        return noteRepository.findByLecturaId(lecturaId).stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con ID: " + id));
        noteRepository.delete(note);
    }
}

