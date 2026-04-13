package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.ColeccionDTO;
import org.example.service.ColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.COLECCIONES_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ColeccionController {

    @Autowired
    private ColeccionService coleccionService;

    @PostMapping
    public ResponseEntity<ColeccionDTO> createColeccion(@Valid @RequestBody ColeccionDTO coleccionDTO) {
        ColeccionDTO createdColeccion = coleccionService.createColeccion(coleccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdColeccion);
    }

    @PutMapping(RoutesConfig.COLECCIONES_BY_ID_REL)
    public ResponseEntity<ColeccionDTO> updateColeccion(@PathVariable Long id, @Valid @RequestBody ColeccionDTO coleccionDTO) {
        ColeccionDTO updatedColeccion = coleccionService.updateColeccion(id, coleccionDTO);
        return ResponseEntity.ok(updatedColeccion);
    }

    @GetMapping(RoutesConfig.COLECCIONES_BY_ID_REL)
    public ResponseEntity<ColeccionDTO> getColeccionById(@PathVariable Long id) {
        return coleccionService.getColeccionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.COLECCIONES_BY_USUARIO_REL)
    public ResponseEntity<List<ColeccionDTO>> getColeccionesByUsuarioId(@PathVariable Long usuarioId) {
        List<ColeccionDTO> colecciones = coleccionService.getColeccionesByUsuarioId(usuarioId);
        return ResponseEntity.ok(colecciones);
    }

    @DeleteMapping(RoutesConfig.COLECCIONES_BY_ID_REL)
    public ResponseEntity<Void> deleteColeccion(@PathVariable Long id) {
        coleccionService.deleteColeccion(id);
        return ResponseEntity.noContent().build();
    }
}


