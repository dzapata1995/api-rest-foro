package com.dzapata.foro.controllers.impl;

import com.dzapata.foro.controllers.TopicoController;
import com.dzapata.foro.dto.request.TopicoRequestDTO;
import com.dzapata.foro.dto.request.TopicoUpdateRequestDTO;
import com.dzapata.foro.dto.response.ListTopicosResponseDTO;
import com.dzapata.foro.dto.response.TopicoDetalleResponseDTO;
import com.dzapata.foro.services.TopicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class TopicoControllerImpl implements TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid TopicoRequestDTO topicoRequestDTO) {
        topicoService.crearTopico(topicoRequestDTO);
        return ResponseEntity.ok("Topico registrado exitosamente.");
    }

    @Override
    @GetMapping("")
    public ResponseEntity<ListTopicosResponseDTO> listarTopicos(
            @RequestParam(required = false) String codigoCurso,
            @RequestParam(required = false) Integer anio,
            Pageable pageable, HttpServletRequest request) {
        ListTopicosResponseDTO response = topicoService.listarTopicos(codigoCurso, anio, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/detalle/{id}")
    public ResponseEntity<TopicoDetalleResponseDTO> listarTopicoById(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.listarTopicoById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDetalleResponseDTO> actualizatTopico(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateRequestDTO request
            ) {
        return ResponseEntity.ok(topicoService.actualizarTopico(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
