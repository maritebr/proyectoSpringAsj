package com.proyectoasj.spring.proyectoasj.service;

import com.proyectoasj.spring.proyectoasj.entities.Usuario;
import com.proyectoasj.spring.proyectoasj.repositories.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioRepositorio iUsuarioRepositorio;
    @Override
    public Iterable<Usuario> obtenerUsuarios() {
        return iUsuarioRepositorio.findAll();
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return iUsuarioRepositorio.save(usuario);
    }
    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return this.iUsuarioRepositorio.findById(id);
    }



    @Override
    public void borrarUsuario(Usuario usuario) {
       this.iUsuarioRepositorio.delete(usuario);
    }


}
