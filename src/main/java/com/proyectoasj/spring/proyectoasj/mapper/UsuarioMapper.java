package com.proyectoasj.spring.proyectoasj.mapper;

import com.proyectoasj.spring.proyectoasj.dto.UsuarioDTO;
import com.proyectoasj.spring.proyectoasj.entities.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO mapToDTO (Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getComentarios());
        return usuarioDTO;
    }
    public static Usuario mapToEntity (UsuarioDTO usuarioDTO){
        Usuario usuario= new Usuario ();
                usuario.setId(usuarioDTO.getId());
                usuario.setNombre(usuarioDTO.getNombre());
                usuario.setApellido(usuarioDTO.getApellido());
                usuario.setEmail(usuarioDTO.getEmail());
                usuario.setComentarios(usuarioDTO.getComentarios());
        return usuario;
    }
}
