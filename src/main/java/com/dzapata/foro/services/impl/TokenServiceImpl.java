package com.dzapata.foro.services.impl;

import com.dzapata.foro.dto.LoginDTO;
import com.dzapata.foro.dto.response.AuthResponseDTO;
import com.dzapata.foro.entities.UsuarioEntity;
import com.dzapata.foro.repositories.UsuarioRepository;
import com.dzapata.foro.security.SecretGenerator;
import com.dzapata.foro.services.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String JWT_SECRET = SecretGenerator.generateSecret();
    private static final long JWT_EXPIRATION_MS = 3600000;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public AuthResponseDTO generateToken(LoginDTO loginDTO) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Usuario no encontrado"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Contraseña invalidas.");
        }

        SecretKey key = getSecretKey();

        String token = Jwts.builder()
                .subject(loginDTO.getEmail())
                .claim("name", usuario.getNombre())
                .claim("role", usuario.getPerfil().getNombre())
                .issuedAt(new Date())
                .expiration( new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key)
                .compact();

        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setAccessToken(token);
        responseDTO.setExpireIn(JWT_EXPIRATION_MS);

        return responseDTO;
    }

    @Override
    public boolean validateToken(String token) {
        try {
            SecretKey key = getSecretKey();
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.");
        }
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    @Override
    public List<GrantedAuthority> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String role = claims.get("role", String.class);

        List<String> roles = role != null ? Collections.singletonList(role) : Collections.emptyList();

        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
