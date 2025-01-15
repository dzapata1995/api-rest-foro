package com.dzapata.foro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaRequestDTO {

    @NotBlank
    private String mensaje;

    @NotBlank
    private String autorEmail;
}
