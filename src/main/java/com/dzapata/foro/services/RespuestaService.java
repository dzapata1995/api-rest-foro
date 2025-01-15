package com.dzapata.foro.services;

import com.dzapata.foro.dto.request.RespuestaRequestDTO;
import com.dzapata.foro.dto.request.RespuestaUpdateRequestDTO;
import com.dzapata.foro.dto.response.RespuestaResponseDTO;

import java.util.List;

public interface RespuestaService {
    RespuestaResponseDTO crearRespuesta(Long topico, RespuestaRequestDTO request);
    List<RespuestaResponseDTO> listarRespuestas(Long topico);
    RespuestaResponseDTO actualizarRespuesta(Long respuesta, RespuestaUpdateRequestDTO request);
    void eliminarRespuesta(Long respuesta);
}
