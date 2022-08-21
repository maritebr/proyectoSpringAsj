package com.proyectoasj.spring.proyectoasj.repositories;

import com.proyectoasj.spring.proyectoasj.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
