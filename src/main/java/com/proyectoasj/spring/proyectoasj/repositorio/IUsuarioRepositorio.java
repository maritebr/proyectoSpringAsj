package com.proyectoasj.spring.proyectoasj.repositorio;

import com.proyectoasj.spring.proyectoasj.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
