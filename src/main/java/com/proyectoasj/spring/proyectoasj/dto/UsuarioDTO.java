package com.proyectoasj.spring.proyectoasj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    @NotEmpty
    @NotNull
    private String nombre;
    @NotEmpty
    @NotNull
    private String apellido;
    @NotEmpty
    @NotNull
    private String email;
    private String comentarios;

}
