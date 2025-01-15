package com.dzapata.foro.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topico")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false, length = 20)
    private String status = "ABIERTO";

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoEntity curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaEntity> respuestas;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
