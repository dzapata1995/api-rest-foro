package com.dzapata.foro.controllers;

import com.dzapata.foro.dto.LoginDTO;
import com.dzapata.foro.dto.response.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<AuthResponseDTO> login(LoginDTO loginDTO);
}
