package com.rickyandmorti.rickyandmorti.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickyandmorti.rickyandmorti.entitys.usuario;
import com.rickyandmorti.rickyandmorti.entitys.Usuario;
import com.rickyandmorti.rickyandmorti.services.UsuarioService;

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
     * --------creo un nuevo usuario--------------
     */
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveusuario(@Valid @RequestBody Usuario usuario) {
        Usuario newusuario = usuarioService.savesUsuario(usuario);
        return ResponseEntity.ok(newusuario);
    }


}
