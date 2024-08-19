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

import com.rickyandmorti.rickyandmorti.entitys.Personaje;
import com.rickyandmorti.rickyandmorti.services.PersonajeService;
import java.util.*;

// @RestController especifica que esta clase es un controlador para solicitudes de API RESTful
//@RequestMapping especifica la URL base para todas las solicitudes manejadas por este controlador.
@RestController
@RequestMapping("/api/v1")
public class PersonajeController {

    private final PersonajeService personajeService;

    // creamos los metodos para manejar las solicitudes http

    // @Autowired ccon 1 solo constructor no necestias autowired
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    /*
     * @RequestBody es una anotación en Spring Framework que se utiliza para
     * vincular el cuerpo de la solicitud HTTP a un parámetro en un método de
     * controlador
     * --------creo un nuevo personaje
     */
    @PostMapping("/personaje")
    public ResponseEntity<Personaje> savePersonaje(@RequestBody Personaje personaje) {
        Personaje newpersonaje = personajeService.savePersonajes(personaje);
        return ResponseEntity.ok(newpersonaje);
    }

    /*
     * --------obtengo la lista de personajes
     * 
     */
    @GetMapping("/personajes")
    public List<Personaje> getAllPersonajes() {
        return personajeService.getAllPersonajes();

    }

    /*
     * get a personaje por ID
     */
    @GetMapping("/personajes/{id}")
    public ResponseEntity<Map<String, String>> getPersonajeById(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeService.getPersonajeById(id);

        if (personaje.isPresent()) {
            return ResponseEntity.ok(Map.of("personaje", personaje.get().toString())); // Replace with appropriate
                                                                                       // mapping
        } else {
            Map<String, String> errorResponse = Map.of("error", "El personaje con ID " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    /*
     * º
     * actualizar personaje por ID
     * 
     */
    @PutMapping("/personajes/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@PathVariable Long id, @RequestBody Personaje personaje) {
        Personaje updatedpersonaje = personajeService.updatePersonaje(id, personaje);
        return ResponseEntity.ok(updatedpersonaje);

    }

    /*
     * borrar un producto por id
     */
    @DeleteMapping("/personajes/{id}")
    public ResponseEntity<String> deletePersonaje(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeService.getPersonajeById(id);
        System.out.println(personaje);
        if (personaje != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El personaje con ID " + id + " no existe");
        }

        personajeService.deletePersonaje(id);
        return ResponseEntity.ok("Producto borrado con exito");

    }

}
