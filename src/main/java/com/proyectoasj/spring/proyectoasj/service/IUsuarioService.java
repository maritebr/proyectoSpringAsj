package com.proyectoasj.spring.proyectoasj.service;

import com.proyectoasj.spring.proyectoasj.modelo.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUsuarioService {

    List<Usuario> obtenerUsuarios();

    Usuario guardarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Long id, Usuario usuario);

    ResponseEntity<Map<String, Boolean>> borrarUsuario(Long id);





}