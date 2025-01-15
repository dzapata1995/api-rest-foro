package com.dzapata.foro.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RespuestaResponseDTO {
    private Long id;
    private String mensaje;
    private Boolean solucion;
    private String autor;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
