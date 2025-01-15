package com.dzapata.foro.services.impl;

import com.dzapata.foro.dto.PaginationLinksDTO;
import com.dzapata.foro.dto.RespuestaDTO;
import com.dzapata.foro.dto.request.TopicoRequestDTO;
import com.dzapata.foro.dto.request.TopicoUpdateRequestDTO;
import com.dzapata.foro.dto.response.ListTopicosResponseDTO;
import com.dzapata.foro.dto.response.TopicoDetalleResponseDTO;
import com.dzapata.foro.dto.response.TopicoResponseDTO;
import com.dzapata.foro.entities.CursoEntity;
import com.dzapata.foro.entities.RespuestaEntity;
import com.dzapata.foro.entities.TopicoEntity;
import com.dzapata.foro.entities.UsuarioEntity;
import com.dzapata.foro.repositories.CursoRepository;
import com.dzapata.foro.repositories.TopicoRepository;
import com.dzapata.foro.repositories.UsuarioRepository;
import com.dzapata.foro.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TopicoServiceImpl implements TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    private final String baseUrl = "/topico";

    @Override
    public void crearTopico(TopicoRequestDTO topicoRequestDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CursoEntity curso = cursoRepository.findByCodigo(topicoRequestDTO.getCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        TopicoEntity nuevoTopico = new TopicoEntity();
        nuevoTopico.setTitulo(topicoRequestDTO.getTitulo());
        nuevoTopico.setMensaje(topicoRequestDTO.getMensaje());
        nuevoTopico.setAutor(usuario);
        nuevoTopico.setCurso(curso);

        topicoRepository.save(nuevoTopico);
    }

    @Override
    public ListTopicosResponseDTO listarTopicos(String codigoCurso, Integer anio, Pageable pageable) {
        Specification<TopicoEntity> specification = Specification.where(null);

        if (codigoCurso != null && !codigoCurso.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.join("curso").get("codigo"), codigoCurso));
        }

        if (anio != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, root.get("fechaCreacion")), anio));
        }

        Page<TopicoEntity> topicosPage = topicoRepository.findAll(specification, pageable);

        List<TopicoResponseDTO> topicosDTO = topicosPage.getContent().stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getFechaActualizacion(),
                        topico.getStatus(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre(),
                        topico.getRespuestas().size()
                )).toList();

        PaginationLinksDTO links = new PaginationLinksDTO(
                getPreviousLink(topicosPage),
                getNextLink(topicosPage)
        );

        return new ListTopicosResponseDTO(
                topicosDTO,
                topicosPage.getTotalElements(),
                topicosPage.getTotalPages(),
                topicosPage.getNumber(),
                links
        );
    }

    @Override
    public TopicoDetalleResponseDTO listarTopicoById(Long id) {
        TopicoEntity topico = topicoRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado");
                });

        TopicoDetalleResponseDTO response = new TopicoDetalleResponseDTO();
        response.setTitulo(topico.getTitulo());
        response.setMensaje(topico.getMensaje());
        response.setFechaCreacion(topico.getFechaCreacion());
        response.setFechaActualizacion(topico.getFechaActualizacion());
        response.setStatus(topico.getStatus());
        response.setAutor(topico.getAutor().getNombre());
        response.setCurso(topico.getCurso().getNombre());

        if (topico.getRespuestas() != null && !topico.getRespuestas().isEmpty()) {
            List<RespuestaEntity> solucion = topico.getRespuestas().stream()
                    .filter(RespuestaEntity::getSolucion)
                    .toList();

            List<RespuestaEntity> noSolucion = topico.getRespuestas().stream()
                    .filter(respuestaEntity -> !respuestaEntity.getSolucion())
                    .toList();

            List<RespuestaEntity> respuestas = Stream.concat(solucion.stream(), noSolucion.stream())
                    .toList();

            List<RespuestaDTO> respuestasDTO = respuestas.stream()
                    .map(respuestaEntity -> {
                        RespuestaDTO respuestaDTO = new RespuestaDTO();
                        respuestaDTO.setMensaje(respuestaEntity.getMensaje());
                        respuestaDTO.setFechaCreacion(respuestaEntity.getFechaCreacion());
                        respuestaDTO.setFechaActualizacion(respuestaEntity.getFechaActualizacion());
                        respuestaDTO.setSolucion(respuestaEntity.getSolucion());
                        respuestaDTO.setAutor(respuestaEntity.getAutor().getNombre());

                        return respuestaDTO;
                    }).toList();

            response.setRespuestas(respuestasDTO);
        } else {
            response.setRespuestas(Collections.emptyList());
        }

        return response;
    }

    @Override
    public TopicoDetalleResponseDTO actualizarTopico(Long id, TopicoUpdateRequestDTO request) {
        TopicoEntity topico = topicoRepository.findById(id).orElseThrow(() -> new InternalError("Topico no encontrado"));

        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());

        topico = topicoRepository.save(topico);
        return listarTopicoById(topico.getId());
    }

    @Override
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new InternalError("Topico no encontrado");
        }

        topicoRepository.deleteById(id);
    }

    private String getPreviousLink(Page<TopicoEntity> topicosPage) {
        if (topicosPage.hasPrevious()) {
            return baseUrl + "?page=" + (topicosPage.getNumber() - 1) + "&size=" + topicosPage.getSize();
        }
        return null;
    }

    private String getNextLink(Page<TopicoEntity> topicosPage) {
        if (topicosPage.hasNext()) {
            return baseUrl + "?page=" + (topicosPage.getNumber() - 1) + "&size=" + topicosPage.getSize();
        }
        return null;
    }
}
