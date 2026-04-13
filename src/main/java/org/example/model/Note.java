package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lecturaId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(length = 100)
    private String referenciaPagina;

    @Column(nullable = false, updatable = false)
    private Long fechaCreacion;

    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = System.currentTimeMillis();
        }
    }
}

