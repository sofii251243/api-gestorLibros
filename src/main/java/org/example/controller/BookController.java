package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.BookDTO;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.BOOKS_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping(RoutesConfig.BOOKS_BY_ID_REL)
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping(RoutesConfig.BOOKS_BY_ID_REL)
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.BOOKS_BY_USUARIO_REL)
    public ResponseEntity<List<BookDTO>> getBooksByUsuarioId(@PathVariable Long usuarioId) {
        List<BookDTO> books = bookService.getBooksByUsuarioId(usuarioId);
        return ResponseEntity.ok(books);
    }

    @GetMapping(RoutesConfig.BOOKS_BY_USUARIO_ESTADO_REL)
    public ResponseEntity<List<BookDTO>> getBooksByEstado(@PathVariable Long usuarioId, @PathVariable String estado) {
        List<BookDTO> books = bookService.getBooksByEstado(usuarioId, estado);
        return ResponseEntity.ok(books);
    }

    @GetMapping(RoutesConfig.BOOKS_BY_SERIE_REL)
    public ResponseEntity<List<BookDTO>> getBooksBySerieId(@PathVariable Long serieId) {
        List<BookDTO> books = bookService.getBooksBySerieId(serieId);
        return ResponseEntity.ok(books);
    }

    @GetMapping(RoutesConfig.BOOKS_SEARCH_TITULO_REL)
    public ResponseEntity<List<BookDTO>> searchByTitulo(
            @RequestParam Long usuarioId,
            @RequestParam String titulo) {
        List<BookDTO> books = bookService.searchByTitulo(usuarioId, titulo);
        return ResponseEntity.ok(books);
    }

    @GetMapping(RoutesConfig.BOOKS_SEARCH_AUTOR_REL)
    public ResponseEntity<List<BookDTO>> searchByAutor(
            @RequestParam Long usuarioId,
            @RequestParam String autor) {
        List<BookDTO> books = bookService.searchByAutor(usuarioId, autor);
        return ResponseEntity.ok(books);
    }

    @PatchMapping(RoutesConfig.BOOKS_UPDATE_PROGRESS_REL)
    public ResponseEntity<BookDTO> updateBookProgress(
            @PathVariable Long id,
            @RequestParam Float progreso) {
        BookDTO updatedBook = bookService.updateBookProgress(id, progreso);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(RoutesConfig.BOOKS_BY_ID_REL)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}


