package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.LecturaColeccionDTO;
import org.example.service.LecturaColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.LECTURA_COLECCION_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class LecturaColeccionController {

    @Autowired
    private LecturaColeccionService lecturaColeccionService;

    @PostMapping
    public ResponseEntity<LecturaColeccionDTO> addBookToColeccion(@Valid @RequestBody LecturaColeccionDTO lecturaColeccionDTO) {
        LecturaColeccionDTO result = lecturaColeccionService.addBookToColeccion(lecturaColeccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping(RoutesConfig.LECTURA_COLECCION_DELETE_REL)
    public ResponseEntity<Void> removeBookFromColeccion(
            @PathVariable Long lecturaId,
            @PathVariable Long coleccionId) {
        lecturaColeccionService.removeBookFromColeccion(lecturaId, coleccionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(RoutesConfig.LECTURA_COLECCION_BY_COLECCION_REL)
    public ResponseEntity<List<LecturaColeccionDTO>> getBooksByColeccion(@PathVariable Long coleccionId) {
        List<LecturaColeccionDTO> books = lecturaColeccionService.getBooksByColeccion(coleccionId);
        return ResponseEntity.ok(books);
    }

    @GetMapping(RoutesConfig.LECTURA_COLECCION_BY_LECTURA_REL)
    public ResponseEntity<List<LecturaColeccionDTO>> getColeccionesByBook(@PathVariable Long lecturaId) {
        List<LecturaColeccionDTO> colecciones = lecturaColeccionService.getColeccionesByBook(lecturaId);
        return ResponseEntity.ok(colecciones);
    }
}


