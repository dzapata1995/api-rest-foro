package com.dzapata.foro.services.impl;

import com.dzapata.foro.dto.request.UsuarioRequestDTO;
import com.dzapata.foro.dto.response.UsuarioResponseDTO;
import com.dzapata.foro.entities.PerfilEntity;
import com.dzapata.foro.entities.UsuarioEntity;
import com.dzapata.foro.repositories.PerfilRepository;
import com.dzapata.foro.repositories.UsuarioRepository;
import com.dzapata.foro.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO request) {
        PerfilEntity perfil = perfilRepository.findById(request.getPerfil())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));

        String password = passwordEncoder.encode(request.getPassword());

        UsuarioEntity usuario = UsuarioEntity.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(password)
                .perfil(perfil)
                .build();

        usuario = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return mapToResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO request) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        PerfilEntity perfil = perfilRepository.findById(request.getPerfil())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setPassword(request.getPassword());
        usuario.setPerfil(perfil);

        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuarioActualizado);
    }

    private UsuarioResponseDTO mapToResponseDTO(UsuarioEntity usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil().getNombre()
        );
    }
}
