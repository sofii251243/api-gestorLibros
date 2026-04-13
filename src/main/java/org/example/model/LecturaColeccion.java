package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecturas_colecciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaColeccion {
    @EmbeddedId
    private LecturaColeccionId id;

    @Column(nullable = false, updatable = false)
    private Long fechaAgregado;

    @PrePersist
    protected void onCreate() {
        if (this.fechaAgregado == null) {
            this.fechaAgregado = System.currentTimeMillis();
        }
    }
}

