package com.dzapata.foro.controllers.impl;

import com.dzapata.foro.controllers.AuthController;
import com.dzapata.foro.dto.LoginDTO;
import com.dzapata.foro.dto.response.AuthResponseDTO;
import com.dzapata.foro.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {

    @Autowired
    private TokenService tokenService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(tokenService.generateToken(loginDTO));
    }
}
