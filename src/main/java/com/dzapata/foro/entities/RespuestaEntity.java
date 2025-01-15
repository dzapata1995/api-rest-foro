package com.dzapata.foro.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean solucion = false;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private TopicoEntity topico;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.solucion = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
