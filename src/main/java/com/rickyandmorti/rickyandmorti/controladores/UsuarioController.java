package com.rickyandmorti.rickyandmorti.controladores;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickyandmorti.rickyandmorti.dto.UsuarioDTO;
import com.rickyandmorti.rickyandmorti.entitys.Usuario;
import com.rickyandmorti.rickyandmorti.services.UsuarioService;
import java.util.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*
     * @RequestBody es una anotación en Spring Framework que se utiliza para
     * vincular el cuerpo de la solicitud HTTP a un parámetro en un método de
     * controlador.
     * anotación @Valid para forzar la validación de ese objeto de acuerdo a las
     * anotaciones que previamente hemos añadido en la definición de dicha clase.
     * 
     * --------creo un nuevo usuario--------------
     */
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveusuario(@Valid @RequestBody Usuario usuario) {
        
        Usuario newusuario = usuarioService.savesUsuario(usuario);
        return ResponseEntity.ok(newusuario);
    }

    /*
     * --------obtengo la lista de usuarios
     * 
     */
    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getAllUsuarios() {
        
            // Obtiene el mapa de usuarios desde el servicio
            Map<String, UsuarioDTO> usuariosMap = usuarioService.getAllUsuarios();

            if (usuariosMap.isEmpty()) {
                // Devuelve un mensaje de error si no se encuentran usuarios
               throw new ResourceNotFoundException("No se encontraron usuarios");
            }

            // Devuelve el mapa de usuarios con estado 200 OK
            return ResponseEntity.ok(Map.of("usuarios", usuariosMap));
          
    }

    /*
     * get a usuario por ID
     */
    @GetMapping("/usuario/id/{id}")
    public ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUsuarioById(id);

        Map<String, Object> response = new HashMap<>();
        if (!usuarioDTO.isPresent()) {
            throw new ResourceNotFoundException("el usuario con el " + id + " no existe ");
            
        } else {
            response.put("usuario", usuarioDTO.get());
            return ResponseEntity.ok(response);
        }
    }

    /*
     * get a usuario por Email
     */
    @GetMapping("/usuario/email/{email}")
    public ResponseEntity<Map<String, Object>> getUsuarioByEmail(@PathVariable String email) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUsuarioByEmail(email);
        Map<String, Object> response = new HashMap<>();
        if (usuarioDTO.isEmpty()) {
            throw new ResourceNotFoundException("el usuario con el " + email + " no existe ");

        } else {
            response.put("usuario", usuarioDTO.get());
            return ResponseEntity.ok(response);
        }
    }

    /*
     * º
     * actualizar usuario por ID
     * 
     * @Valid para forzar la validación de ese objeto de acuerdo a las
     * anotaciones que previamente hemos añadido en la definición de dicha clase
     */
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updatePersonaje(@Valid @PathVariable Long id, @RequestBody Usuario personaje) {
        Usuario updateUsuario = usuarioService.updateUsuario(id, personaje);
        return ResponseEntity.ok(updateUsuario);

    }

    /*
     * borrar un usuario por id
     */
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long id) {
        boolean fueEliminado = usuarioService.deleteUsuario(id);

        if (fueEliminado) {
            
            return ResponseEntity.ok(Map.of("message", "Usuario borrado con éxito"));
        } else {
            // El usuario no fue encontrado o ya estaba inactivo
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El usuario con ID " + id + " no existe o esta eliminado"));
        }
    }

}
