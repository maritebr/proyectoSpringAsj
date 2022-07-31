package com.proyectoasj.spring.proyectoasj.controller;

import com.proyectoasj.spring.proyectoasj.modelo.Usuario;
import com.proyectoasj.spring.proyectoasj.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proyectoAdoptame")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    //enlisto los empleados
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    //guardo usuario de mi form
    @PostMapping("/usuarios")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario modificarUsuario(@PathVariable Long id, @RequestBody Usuario datosUsuario){
        return usuarioService.actualizarUsuario(id, datosUsuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
        return usuarioService.borrarUsuario(id);
    }
}
