package org.example.service.impl;

import org.example.dto.LecturaColeccionDTO;
import org.example.exception.DuplicateResourceException;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.LecturaColeccionMapper;
import org.example.model.LecturaColeccion;
import org.example.model.LecturaColeccionId;
import org.example.repository.BookRepository;
import org.example.repository.ColeccionRepository;
import org.example.repository.LecturaColeccionRepository;
import org.example.service.LecturaColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LecturaColeccionServiceImpl implements LecturaColeccionService {

    @Autowired
    private LecturaColeccionRepository lecturaColeccionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ColeccionRepository coleccionRepository;

    @Autowired
    private LecturaColeccionMapper lecturaColeccionMapper;

    @Override
    public LecturaColeccionDTO addBookToColeccion(LecturaColeccionDTO lecturaColeccionDTO) {
        if (!bookRepository.existsById(lecturaColeccionDTO.getLecturaId())) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + lecturaColeccionDTO.getLecturaId());
        }
        if (!coleccionRepository.existsById(lecturaColeccionDTO.getColeccionId())) {
            throw new ResourceNotFoundException("Colección no encontrada con ID: " + lecturaColeccionDTO.getColeccionId());
        }

        if (lecturaColeccionRepository.existsByIdLecturaIdAndIdColeccionId(
                lecturaColeccionDTO.getLecturaId(), lecturaColeccionDTO.getColeccionId())) {
            throw new DuplicateResourceException("El libro ya está en la colección");
        }

        LecturaColeccion lecturaColeccion = lecturaColeccionMapper.toEntity(lecturaColeccionDTO);
        LecturaColeccion savedLecturaColeccion = lecturaColeccionRepository.save(lecturaColeccion);
        return lecturaColeccionMapper.toDTO(savedLecturaColeccion);
    }

    @Override
    public void removeBookFromColeccion(Long lecturaId, Long coleccionId) {
        LecturaColeccionId id = new LecturaColeccionId(lecturaId, coleccionId);
        LecturaColeccion lecturaColeccion = lecturaColeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relación no encontrada"));
        lecturaColeccionRepository.delete(lecturaColeccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LecturaColeccionDTO> getBooksByColeccion(Long coleccionId) {
        if (!coleccionRepository.existsById(coleccionId)) {
            throw new ResourceNotFoundException("Colección no encontrada con ID: " + coleccionId);
        }
        return lecturaColeccionRepository.findByIdColeccionId(coleccionId).stream()
                .map(lecturaColeccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LecturaColeccionDTO> getColeccionesByBook(Long lecturaId) {
        if (!bookRepository.existsById(lecturaId)) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + lecturaId);
        }
        return lecturaColeccionRepository.findByIdLecturaId(lecturaId).stream()
                .map(lecturaColeccionMapper::toDTO)
                .collect(Collectors.toList());
    }
}

