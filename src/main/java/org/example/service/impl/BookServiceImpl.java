package org.example.service.impl;

import org.example.dto.BookDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.ValidationException;
import org.example.mapper.BookMapper;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.UserRepository;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        if (!userRepository.existsById(bookDTO.getUsuarioId())) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + bookDTO.getUsuarioId());
        }
        
        if (bookDTO.getProgreso() < 0 || bookDTO.getProgreso() > 100) {
            throw new ValidationException("El progreso debe estar entre 0 y 100");
        }

        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con ID: " + id));

        if (bookDTO.getProgreso() < 0 || bookDTO.getProgreso() > 100) {
            throw new ValidationException("El progreso debe estar entre 0 y 100");
        }

        book.setTitulo(bookDTO.getTitulo());
        book.setAutor(bookDTO.getAutor());
        book.setFormato(bookDTO.getFormato());
        book.setProgreso(bookDTO.getProgreso());
        book.setEstado(bookDTO.getEstado());
        book.setCoverPath(bookDTO.getCoverPath());
        book.setSerieId(bookDTO.getSerieId());

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByUsuarioId(Long usuarioId) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return bookRepository.findByUsuarioId(usuarioId).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByEstado(Long usuarioId, String estado) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return bookRepository.findByUsuarioIdAndEstado(usuarioId, estado).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getBooksBySerieId(Long serieId) {
        return bookRepository.findBySerieId(serieId).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> searchByTitulo(Long usuarioId, String titulo) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return bookRepository.findByUsuarioIdAndTituloContaining(usuarioId, titulo).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> searchByAutor(Long usuarioId, String autor) {
        if (!userRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }
        return bookRepository.findByUsuarioIdAndAutorContaining(usuarioId, autor).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        bookRepository.delete(book);
    }

    @Override
    public BookDTO updateBookProgress(Long id, Float progreso) {
        if (progreso < 0 || progreso > 100) {
            throw new ValidationException("El progreso debe estar entre 0 y 100");
        }

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con ID: " + id));

        book.setProgreso(progreso);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }
}

