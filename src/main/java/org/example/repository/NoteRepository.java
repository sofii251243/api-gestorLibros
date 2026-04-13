package org.example.repository;

import org.example.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByLecturaId(Long lecturaId);
    boolean existsByIdAndLecturaId(Long id, Long lecturaId);
}

