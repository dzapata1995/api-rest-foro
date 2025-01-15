package com.dzapata.foro.controllers;

import com.dzapata.foro.dto.request.UsuarioRequestDTO;
import com.dzapata.foro.dto.response.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioController {
    ResponseEntity<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO request);
    ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios();
    ResponseEntity<UsuarioResponseDTO> actualizarUsuario(Long id, UsuarioRequestDTO request);
}
