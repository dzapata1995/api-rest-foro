package com.dzapata.foro.services;

import com.dzapata.foro.dto.request.UsuarioRequestDTO;
import com.dzapata.foro.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO request);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(Long id);
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO request);
}
