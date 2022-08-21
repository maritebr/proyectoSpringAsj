package com.proyectoasj.spring.proyectoasj.service;

import com.proyectoasj.spring.proyectoasj.entities.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public interface IUsuarioService {
    Iterable<Usuario> obtenerUsuarios();
    //List<Usuario> obtenerUsuarios();
    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    void borrarUsuario(Usuario usuario);


}