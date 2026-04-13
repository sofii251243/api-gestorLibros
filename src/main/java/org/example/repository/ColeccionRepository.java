package org.example.repository;

import org.example.model.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {
    List<Coleccion> findByUsuarioId(Long usuarioId);
    Optional<Coleccion> findByIdAndUsuarioId(Long id, Long usuarioId);
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
}

