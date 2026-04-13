package org.example.repository;

import org.example.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByLecturaId(Long lecturaId);
    boolean existsByIdAndLecturaId(Long id, Long lecturaId);
}

