package com.proyectoasj.spring.proyectoasj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoasj.spring.proyectoasj.dto.UsuarioDTO;
import com.proyectoasj.spring.proyectoasj.entities.Usuario;
import com.proyectoasj.spring.proyectoasj.mapper.UsuarioMapper;
import com.proyectoasj.spring.proyectoasj.service.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proyectoAdoptame")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

//    @Autowired
//    UsuarioService usuarioService;

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Muestro todos los usuarios registrados
    @GetMapping("/usuarios")
    public ResponseEntity<?> mostrarUsuarios(){

        List<Usuario> usuarios = (List<Usuario>)this.usuarioService.obtenerUsuarios();
        List<UsuarioDTO> listaDTO = usuarios.stream()
                .map(usu -> UsuarioMapper.mapToDTO(usu))
                .collect(Collectors.toList()); // almaceno mi lista
        Map<String, Object> mensajeBody = new HashMap<>();
        if(usuarios.isEmpty()){
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de clientes");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", listaDTO);
        return ResponseEntity.ok(mensajeBody);
    }

    //alta de nuevos usuarios
    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, Object> validacion = new HashMap<>();
            bindingResult.getFieldErrors()
                    .forEach(error -> validacion.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validacion);
        }

        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.usuarioService.guardarUsuario(UsuarioMapper.mapToEntity(usuarioDTO)));
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }

    //buscar usuarios por id
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Usuario> usuarioO = this.usuarioService.buscarPorId(id);
        if(usuarioO.isPresent()){
            mensajeBody.put("estado", Boolean.TRUE);
            mensajeBody.put("datos", usuarioO.get());
            return ResponseEntity.ok(mensajeBody);
        }else{
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("datos", "El id solicitado no existe");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
    }

    //actualizar/modificar usuarios
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Usuario> usuarioModif = this.usuarioService.buscarPorId(id);

        if(!usuarioModif.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "El usuario no existe ");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            Usuario usuarioModificado = usuarioModif.get();
            usuarioModificado.setNombre(usuario.getNombre());
            usuarioModificado.setApellido(usuario.getApellido());
            usuarioModificado.setEmail(usuario.getEmail());
            usuarioModificado.setComentarios(usuario.getComentarios());
            mensajeBody.put("datos", this.usuarioService.guardarUsuario(usuarioModificado));
        }
        return ResponseEntity.ok(mensajeBody);
    }

    //eliminar datos/usuarios
    @DeleteMapping("/usuarios/{id}")
    public  ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Usuario> usuarioDelete = this.usuarioService.buscarPorId(id);
        if (!usuarioDelete.isPresent()){
            mensajeBody.put("datos","El id no existe en la base de datos");
            return ResponseEntity.badRequest().body(mensajeBody);
        }else{
            this.usuarioService.borrarUsuario(usuarioDelete.get());
            mensajeBody.put("datos","Se ha borrado el usuario correctamente");
            return ResponseEntity.ok(mensajeBody);
        }

    }



}
