package com.dzapata.foro.controllers;

import com.dzapata.foro.dto.request.RespuestaRequestDTO;
import com.dzapata.foro.dto.request.RespuestaUpdateRequestDTO;
import com.dzapata.foro.dto.response.RespuestaResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RespuestaController {
    ResponseEntity<RespuestaResponseDTO> crearRespuesta(Long topicoId, RespuestaRequestDTO request);
    ResponseEntity<List<RespuestaResponseDTO>> listarRespuestas(Long topicoId);
    ResponseEntity<RespuestaResponseDTO> actualizarRespuesta(Long topicoId, RespuestaUpdateRequestDTO request);
    ResponseEntity<Void> eliminarRespuesta(Long respuestaId);
}
