package org.example.repository;

import org.example.model.LecturaColeccion;
import org.example.model.LecturaColeccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LecturaColeccionRepository extends JpaRepository<LecturaColeccion, LecturaColeccionId> {
    List<LecturaColeccion> findByIdColeccionId(Long coleccionId);
    List<LecturaColeccion> findByIdLecturaId(Long lecturaId);
    boolean existsByIdLecturaIdAndIdColeccionId(Long lecturaId, Long coleccionId);
}

