package com.dzapata.foro.dto.response;

import com.dzapata.foro.dto.RespuestaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDetalleResponseDTO {
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String status;
    private String autor;
    private String curso;
    private List<RespuestaDTO> respuestas;
}
