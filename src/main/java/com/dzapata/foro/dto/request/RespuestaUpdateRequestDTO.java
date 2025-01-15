package com.dzapata.foro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RespuestaUpdateRequestDTO {

    @NotBlank
    private String mensaje;
}
