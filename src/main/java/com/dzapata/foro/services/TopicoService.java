package com.dzapata.foro.services;

import com.dzapata.foro.dto.request.TopicoRequestDTO;
import com.dzapata.foro.dto.request.TopicoUpdateRequestDTO;
import com.dzapata.foro.dto.response.ListTopicosResponseDTO;
import com.dzapata.foro.dto.response.TopicoDetalleResponseDTO;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

    void crearTopico(TopicoRequestDTO topicoRequestDTO);

    ListTopicosResponseDTO listarTopicos(String codigoCurso, Integer anio, Pageable pageable);

    TopicoDetalleResponseDTO listarTopicoById(Long id);

    TopicoDetalleResponseDTO actualizarTopico(Long id, TopicoUpdateRequestDTO request);

    void eliminarTopico(Long id);
}
