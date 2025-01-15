package com.dzapata.foro.controllers.impl;

import com.dzapata.foro.controllers.UsuarioController;
import com.dzapata.foro.dto.request.UsuarioRequestDTO;
import com.dzapata.foro.dto.response.UsuarioResponseDTO;
import com.dzapata.foro.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody @Valid UsuarioRequestDTO request) {
        UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, request);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
