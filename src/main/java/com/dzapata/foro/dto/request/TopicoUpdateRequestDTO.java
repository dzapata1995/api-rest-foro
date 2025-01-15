package com.dzapata.foro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoUpdateRequestDTO {

    @NotBlank(message = "El titulo no puede estar vacio.")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacio.")
    private String mensaje;
}
