package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false, length = 500)
    private String rutaArchivo;

    @Column(nullable = false, length = 255)
    private String nombreArchivo;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String formato;

    @Column(length = 255)
    private String autor;

    private Long serieId;

    @Column(nullable = false)
    private Float progreso;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(length = 500)
    private String coverPath;

    @Column(nullable = false, updatable = false)
    private Long fechaCreacion;

    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = System.currentTimeMillis();
        }
        if (this.progreso == null) {
            this.progreso = 0f;
        }
        if (this.estado == null) {
            this.estado = "PENDIENTE";
        }
    }
}

