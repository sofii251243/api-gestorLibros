package org.example.repository;

import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUsuarioId(Long usuarioId);
    List<Book> findByUsuarioIdAndEstado(Long usuarioId, String estado);
    List<Book> findBySerieId(Long serieId);
    
    @Query("SELECT b FROM Book b WHERE b.usuarioId = :usuarioId AND b.titulo LIKE %:titulo%")
    List<Book> findByUsuarioIdAndTituloContaining(@Param("usuarioId") Long usuarioId, @Param("titulo") String titulo);
    
    @Query("SELECT b FROM Book b WHERE b.usuarioId = :usuarioId AND b.autor LIKE %:autor%")
    List<Book> findByUsuarioIdAndAutorContaining(@Param("usuarioId") Long usuarioId, @Param("autor") String autor);
    
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
}

