package org.example.service.impl;

import org.example.dto.ColeccionDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ColeccionMapper;
import org.example.model.Coleccion;
import org.example.repository.ColeccionRepository;
import org.example.repository.UserRepository;
import org.example.service.ColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ColeccionServiceImpl implements ColeccionService {

    @Autowired
    private ColeccionRepository coleccionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColeccionMapper coleccionMapper;

    @Override
    public ColeccionDTO createColeccion(ColeccionDTO coleccionDTO) {
        if (!userRepository.existsById(coleccionDTO.getUsuarioId())) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + coleccionDTO.getUsuarioId());
        }
        Coleccion coleccion = coleccionMapper.toEntity(coleccionDTO);
        Coleccion savedColeccion = coleccionRepository.save(coleccion);
        return coleccionMapper.toDTO(savedColeccion);
    }

    @Override
    public ColeccionDTO updateColeccion(Long id, ColeccionDTO coleccionDTO) {
        Coleccion coleccion = coleccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colección no encontrada con ID: " + id));

        coleccion.setNombre(coleccionDTO.getNombre());
        Coleccion updatedColeccion = coleccionRepository.save(coleccion);
        return coleccionMapper.toDTO(updatedColeccion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColeccionDTO> getColeccionById(Long id) {
        return coleccionRepository.findById(id).map(coleccionMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColeccionDTO> getColeccionesByUsuarioId(Long usuarioId) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return coleccionRepository.findByUsuarioId(usuarioId).stream()
                .map(coleccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteColeccion(Long id) {
        Coleccion coleccion = coleccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colección no encontrada con ID: " + id));
        coleccionRepository.delete(coleccion);
    }
}

