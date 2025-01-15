package com.dzapata.foro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoRequestDTO {

    @NotBlank(message = "El titulo no puede estar vacio.")
    @Size(max = 200, message = "El titulo no puede superar los 200 caracteres")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacio.")
    private String mensaje;

    @NotBlank(message = "El curso no puede estar vacio.")
    private String curso;
}
