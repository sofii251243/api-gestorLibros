package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.SerieDTO;
import org.example.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.SERIES_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PostMapping
    public ResponseEntity<SerieDTO> createSerie(@Valid @RequestBody SerieDTO serieDTO) {
        SerieDTO createdSerie = serieService.createSerie(serieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSerie);
    }

    @PutMapping(RoutesConfig.SERIES_BY_ID_REL)
    public ResponseEntity<SerieDTO> updateSerie(@PathVariable Long id, @Valid @RequestBody SerieDTO serieDTO) {
        SerieDTO updatedSerie = serieService.updateSerie(id, serieDTO);
        return ResponseEntity.ok(updatedSerie);
    }

    @GetMapping(RoutesConfig.SERIES_BY_ID_REL)
    public ResponseEntity<SerieDTO> getSerieById(@PathVariable Long id) {
        return serieService.getSerieById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.SERIES_BY_USUARIO_REL)
    public ResponseEntity<List<SerieDTO>> getSeriesByUsuarioId(@PathVariable Long usuarioId) {
        List<SerieDTO> series = serieService.getSeriesByUsuarioId(usuarioId);
        return ResponseEntity.ok(series);
    }

    @DeleteMapping(RoutesConfig.SERIES_BY_ID_REL)
    public ResponseEntity<Void> deleteSerie(@PathVariable Long id) {
        serieService.deleteSerie(id);
        return ResponseEntity.noContent().build();
    }
}


