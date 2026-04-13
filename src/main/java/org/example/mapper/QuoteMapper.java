package org.example.mapper;

import org.example.dto.QuoteDTO;
import org.example.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapper {
    public QuoteDTO toDTO(Quote quote) {
        if (quote == null) {
            return null;
        }
        QuoteDTO dto = new QuoteDTO();
        dto.setId(quote.getId());
        dto.setLecturaId(quote.getLecturaId());
        dto.setTextoCitado(quote.getTextoCitado());
        dto.setReferenciaPagina(quote.getReferenciaPagina());
        dto.setComentario(quote.getComentario());
        dto.setFechaCreacion(quote.getFechaCreacion());
        return dto;
    }

    public Quote toEntity(QuoteDTO dto) {
        if (dto == null) {
            return null;
        }
        Quote quote = new Quote();
        quote.setId(dto.getId());
        quote.setLecturaId(dto.getLecturaId());
        quote.setTextoCitado(dto.getTextoCitado());
        quote.setReferenciaPagina(dto.getReferenciaPagina());
        quote.setComentario(dto.getComentario());
        quote.setFechaCreacion(dto.getFechaCreacion());
        return quote;
    }
}

