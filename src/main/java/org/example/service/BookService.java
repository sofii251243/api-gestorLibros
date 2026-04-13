package org.example.service;

import org.example.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    Optional<BookDTO> getBookById(Long id);
    List<BookDTO> getBooksByUsuarioId(Long usuarioId);
    List<BookDTO> getBooksByEstado(Long usuarioId, String estado);
    List<BookDTO> getBooksBySerieId(Long serieId);
    List<BookDTO> searchByTitulo(Long usuarioId, String titulo);
    List<BookDTO> searchByAutor(Long usuarioId, String autor);
    void deleteBook(Long id);
    BookDTO updateBookProgress(Long id, Float progreso);
}

