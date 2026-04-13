package org.example.mapper;

import org.example.dto.BookDTO;
import org.example.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setUsuarioId(book.getUsuarioId());
        dto.setRutaArchivo(book.getRutaArchivo());
        dto.setNombreArchivo(book.getNombreArchivo());
        dto.setTitulo(book.getTitulo());
        dto.setFormato(book.getFormato());
        dto.setAutor(book.getAutor());
        dto.setSerieId(book.getSerieId());
        dto.setProgreso(book.getProgreso());
        dto.setEstado(book.getEstado());
        dto.setCoverPath(book.getCoverPath());
        dto.setFechaCreacion(book.getFechaCreacion());
        return dto;
    }

    public Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setId(dto.getId());
        book.setUsuarioId(dto.getUsuarioId());
        book.setRutaArchivo(dto.getRutaArchivo());
        book.setNombreArchivo(dto.getNombreArchivo());
        book.setTitulo(dto.getTitulo());
        book.setFormato(dto.getFormato());
        book.setAutor(dto.getAutor());
        book.setSerieId(dto.getSerieId());
        book.setProgreso(dto.getProgreso());
        book.setEstado(dto.getEstado());
        book.setCoverPath(dto.getCoverPath());
        book.setFechaCreacion(dto.getFechaCreacion());
        return book;
    }
}

