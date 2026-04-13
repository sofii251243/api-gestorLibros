package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.QuoteDTO;
import org.example.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.QUOTES_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteDTO> createQuote(@Valid @RequestBody QuoteDTO quoteDTO) {
        QuoteDTO createdQuote = quoteService.createQuote(quoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuote);
    }

    @PutMapping(RoutesConfig.QUOTES_BY_ID_REL)
    public ResponseEntity<QuoteDTO> updateQuote(@PathVariable Long id, @Valid @RequestBody QuoteDTO quoteDTO) {
        QuoteDTO updatedQuote = quoteService.updateQuote(id, quoteDTO);
        return ResponseEntity.ok(updatedQuote);
    }

    @GetMapping(RoutesConfig.QUOTES_BY_ID_REL)
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable Long id) {
        return quoteService.getQuoteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.QUOTES_BY_LECTURA_REL)
    public ResponseEntity<List<QuoteDTO>> getQuotesByLecturaId(@PathVariable Long lecturaId) {
        List<QuoteDTO> quotes = quoteService.getQuotesByLecturaId(lecturaId);
        return ResponseEntity.ok(quotes);
    }

    @DeleteMapping(RoutesConfig.QUOTES_BY_ID_REL)
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}


