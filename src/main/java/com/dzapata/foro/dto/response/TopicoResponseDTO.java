package com.dzapata.foro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoResponseDTO {
    private long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String status;
    private String autor;
    private String curso;
    private int totalRespuesta;
}
