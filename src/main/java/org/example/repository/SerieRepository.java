package org.example.repository;

import org.example.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    List<Serie> findByUsuarioId(Long usuarioId);
    Optional<Serie> findByIdAndUsuarioId(Long id, Long usuarioId);
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
}

