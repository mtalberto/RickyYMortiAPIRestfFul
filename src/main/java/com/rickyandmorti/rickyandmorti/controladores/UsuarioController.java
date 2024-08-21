package com.rickyandmorti.rickyandmorti.controladores;

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
    public ResponseEntity<Map<String, String>> getAllUsuarios() {
        try {
            // Retrieve the map from the service
            Map<String, String> usuarios = usuarioService.getAlUsuarios();

            if (usuarios.isEmpty()) {
                // Return 404 Not Found if no usuarios are available
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "La lista esta vacia"));
            }

            // devuelve ResponseEntity con 200 OK status
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            // devuelve a 500 Internal Server Error en caso de error inesparado

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Un error ha ocurrido: " + e.getMessage()));
        }
    }

    /*
     * get a usuario por ID
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, String>> getUsuarioID(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(Map.of("usuario", usuario.get().toString()));
        } else {
            Map<String, String> errorResponse = Map.of("error", "El usuario con ID " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
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
        boolean borrarUsuario = usuarioService.deleteUsuario(id);

        if (borrarUsuario) {
            // devuelve un mensaje si el personaje ha sido borrado con exito
            return ResponseEntity.ok(Map.of("message", "Personaje borrado con éxito"));
        } else {
            // devuelve un 404 si no se ha encontrado un personaje
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El personaje con ID " + id + " no existe"));
        }
    }

}
