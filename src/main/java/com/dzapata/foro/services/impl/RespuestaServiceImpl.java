package com.dzapata.foro.services.impl;

import com.dzapata.foro.dto.request.RespuestaRequestDTO;
import com.dzapata.foro.dto.request.RespuestaUpdateRequestDTO;
import com.dzapata.foro.dto.response.RespuestaResponseDTO;
import com.dzapata.foro.entities.RespuestaEntity;
import com.dzapata.foro.entities.TopicoEntity;
import com.dzapata.foro.entities.UsuarioEntity;
import com.dzapata.foro.repositories.RespuestaRepository;
import com.dzapata.foro.repositories.TopicoRepository;
import com.dzapata.foro.repositories.UsuarioRepository;
import com.dzapata.foro.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public RespuestaResponseDTO crearRespuesta(Long topicoId, RespuestaRequestDTO request) {
        TopicoEntity topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        UsuarioEntity autor = usuarioRepository.findByEmail(request.getAutorEmail())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        RespuestaEntity respuesta = RespuestaEntity.builder()
                .mensaje(request.getMensaje())
                .autor(autor)
                .topico(topico)
                .build();

        respuesta = respuestaRepository.save(respuesta);

        return mapToResponseDTO(respuesta);
    }

    @Override
    public List<RespuestaResponseDTO> listarRespuestas(Long topicoId) {
        TopicoEntity topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return topico.getRespuestas().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RespuestaResponseDTO actualizarRespuesta(Long respuestaId, RespuestaUpdateRequestDTO request) {
        RespuestaEntity respuesta = respuestaRepository.findById(respuestaId)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuesta.setMensaje(request.getMensaje());

        RespuestaEntity respuestaActualizada = respuestaRepository.save(respuesta);

        return mapToResponseDTO(respuestaActualizada);
    }

    @Override
    @Transactional
    public void eliminarRespuesta(Long respuestaId) {
        RespuestaEntity respuesta = respuestaRepository.findById(respuestaId)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuestaRepository.delete(respuesta);
    }

    private RespuestaResponseDTO mapToResponseDTO(RespuestaEntity respuesta) {
        RespuestaResponseDTO dto = new RespuestaResponseDTO();
        dto.setId(respuesta.getId());
        dto.setMensaje(respuesta.getMensaje());
        dto.setSolucion(respuesta.getSolucion());
        dto.setAutor(respuesta.getAutor().getNombre());
        dto.setFechaCreacion(respuesta.getFechaCreacion());
        dto.setFechaActualizacion(respuesta.getFechaActualizacion());
        return dto;
    }
}
