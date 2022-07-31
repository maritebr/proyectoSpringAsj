package com.proyectoasj.spring.proyectoasj.service;

import com.proyectoasj.spring.proyectoasj.excepciones.ResourceNotFoundException;
import com.proyectoasj.spring.proyectoasj.modelo.Usuario;
import com.proyectoasj.spring.proyectoasj.repositorio.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    IUsuarioRepositorio iUsuarioRepositorio;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return iUsuarioRepositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return iUsuarioRepositorio.save(usuario);
    }

    public ResponseEntity<Usuario> buscarPorId(Long id){
        Usuario usuario = iUsuarioRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ID solicitado no existe."));
        return ResponseEntity.ok(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosUsuario){
        Usuario usuario = iUsuarioRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ID solicitado no existe."));

        usuario.setNombre(datosUsuario.getNombre());
        usuario.setApellido(datosUsuario.getApellido());
        usuario.setEmail(datosUsuario.getEmail());
        usuario.setComentarios(datosUsuario.getComentarios());

        Usuario usuarioModificado = iUsuarioRepositorio.save(usuario);
        return usuarioModificado;
    }

    public ResponseEntity<Map<String, Boolean>> borrarUsuario(Long id){
        Usuario usuario = iUsuarioRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ID solicitado no existe."));

        iUsuarioRepositorio.delete(usuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
