package org.example.service;

import org.example.dto.QuoteDTO;
import java.util.List;
import java.util.Optional;

public interface QuoteService {
    QuoteDTO createQuote(QuoteDTO quoteDTO);
    QuoteDTO updateQuote(Long id, QuoteDTO quoteDTO);
    Optional<QuoteDTO> getQuoteById(Long id);
    List<QuoteDTO> getQuotesByLecturaId(Long lecturaId);
    void deleteQuote(Long id);
}

