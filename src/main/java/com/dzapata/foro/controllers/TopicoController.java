package com.dzapata.foro.controllers;

import com.dzapata.foro.dto.request.TopicoRequestDTO;
import com.dzapata.foro.dto.request.TopicoUpdateRequestDTO;
import com.dzapata.foro.dto.response.ListTopicosResponseDTO;
import com.dzapata.foro.dto.response.TopicoDetalleResponseDTO;
import com.dzapata.foro.dto.response.TopicoResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface TopicoController {

    ResponseEntity<String> registrarTopico(TopicoRequestDTO topicoRequestDTO);

    ResponseEntity<ListTopicosResponseDTO> listarTopicos(
            String codigoCurso, Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request
    );

    ResponseEntity<TopicoDetalleResponseDTO> listarTopicoById(Long id);

    ResponseEntity<TopicoDetalleResponseDTO> actualizatTopico(Long id, TopicoUpdateRequestDTO req);

    ResponseEntity<Void> eliminarTopico(Long id);
}
