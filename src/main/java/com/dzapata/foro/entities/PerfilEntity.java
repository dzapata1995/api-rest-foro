package com.dzapata.foro.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "perfil")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;
}
