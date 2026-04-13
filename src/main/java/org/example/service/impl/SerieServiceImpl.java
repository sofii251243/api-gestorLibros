package org.example.service.impl;

import org.example.dto.SerieDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.SerieMapper;
import org.example.model.Serie;
import org.example.repository.SerieRepository;
import org.example.repository.UserRepository;
import org.example.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SerieServiceImpl implements SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerieMapper serieMapper;

    @Override
    public SerieDTO createSerie(SerieDTO serieDTO) {
        if (!userRepository.existsById(serieDTO.getUsuarioId())) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + serieDTO.getUsuarioId());
        }
        Serie serie = serieMapper.toEntity(serieDTO);
        Serie savedSerie = serieRepository.save(serie);
        return serieMapper.toDTO(savedSerie);
    }

    @Override
    public SerieDTO updateSerie(Long id, SerieDTO serieDTO) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serie no encontrada con ID: " + id));

        serie.setNombre(serieDTO.getNombre());
        Serie updatedSerie = serieRepository.save(serie);
        return serieMapper.toDTO(updatedSerie);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SerieDTO> getSerieById(Long id) {
        return serieRepository.findById(id).map(serieMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SerieDTO> getSeriesByUsuarioId(Long usuarioId) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return serieRepository.findByUsuarioId(usuarioId).stream()
                .map(serieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSerie(Long id) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serie no encontrada con ID: " + id));
        serieRepository.delete(serie);
    }
}

