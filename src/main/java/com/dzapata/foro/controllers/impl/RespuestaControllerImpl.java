package com.dzapata.foro.controllers.impl;

import com.dzapata.foro.controllers.RespuestaController;
import com.dzapata.foro.dto.request.RespuestaRequestDTO;
import com.dzapata.foro.dto.request.RespuestaUpdateRequestDTO;
import com.dzapata.foro.dto.response.RespuestaResponseDTO;
import com.dzapata.foro.services.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topico/{topicoId}/respuestas")
public class RespuestaControllerImpl implements RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Override
    @PostMapping
    public ResponseEntity<RespuestaResponseDTO> crearRespuesta(
            @PathVariable Long topicoId,
            @Valid @RequestBody RespuestaRequestDTO request) {
        RespuestaResponseDTO respuesta = respuestaService.crearRespuesta(topicoId, request);
        return ResponseEntity.ok(respuesta);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RespuestaResponseDTO>> listarRespuestas(@PathVariable Long topicoId) {
        List<RespuestaResponseDTO> respuestas = respuestaService.listarRespuestas(topicoId);
        return ResponseEntity.ok(respuestas);
    }

    @Override
    @PutMapping("/{respuestaId}")
    public ResponseEntity<RespuestaResponseDTO> actualizarRespuesta(
            @PathVariable Long respuestaId,
            @Valid @RequestBody RespuestaUpdateRequestDTO request) {
        RespuestaResponseDTO respuesta = respuestaService.actualizarRespuesta(respuestaId, request);
        return ResponseEntity.ok(respuesta);
    }

    @Override
    @DeleteMapping("/{respuestaId}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long respuestaId) {
        respuestaService.eliminarRespuesta(respuestaId);
        return ResponseEntity.noContent().build();
    }
}
