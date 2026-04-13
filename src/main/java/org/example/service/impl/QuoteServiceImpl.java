package org.example.service.impl;

import org.example.dto.QuoteDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.QuoteMapper;
import org.example.model.Quote;
import org.example.repository.BookRepository;
import org.example.repository.QuoteRepository;
import org.example.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private QuoteMapper quoteMapper;

    @Override
    public QuoteDTO createQuote(QuoteDTO quoteDTO) {
        if (!bookRepository.existsById(quoteDTO.getLecturaId())) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + quoteDTO.getLecturaId());
        }
        Quote quote = quoteMapper.toEntity(quoteDTO);
        Quote savedQuote = quoteRepository.save(quote);
        return quoteMapper.toDTO(savedQuote);
    }

    @Override
    public QuoteDTO updateQuote(Long id, QuoteDTO quoteDTO) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));

        quote.setTextoCitado(quoteDTO.getTextoCitado());
        quote.setReferenciaPagina(quoteDTO.getReferenciaPagina());
        quote.setComentario(quoteDTO.getComentario());

        Quote updatedQuote = quoteRepository.save(quote);
        return quoteMapper.toDTO(updatedQuote);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuoteDTO> getQuoteById(Long id) {
        return quoteRepository.findById(id).map(quoteMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDTO> getQuotesByLecturaId(Long lecturaId) {
        if (!bookRepository.existsById(lecturaId)) {
            throw new ResourceNotFoundException("Libro no encontrado con ID: " + lecturaId);
        }
        return quoteRepository.findByLecturaId(lecturaId).stream()
                .map(quoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuote(Long id) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id));
        quoteRepository.delete(quote);
    }
}

