package com.dzapata.foro.services;

import com.dzapata.foro.dto.LoginDTO;
import com.dzapata.foro.dto.response.AuthResponseDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface TokenService {
    AuthResponseDTO generateToken(LoginDTO loginDTO);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    List<GrantedAuthority> getRolesFromToken(String token);
}
