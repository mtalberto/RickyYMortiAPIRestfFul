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
import jakarta.validation.Valid;
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
     * controlador.
     * anotación @Valid para forzar la validación de ese objeto de acuerdo a las
     * anotaciones que previamente hemos añadido en la definición de dicha clase.
     * --------creo un nuevo personaje--------------
     */
    @PostMapping("/personaje")
    public ResponseEntity<Personaje> savePersonaje(@Valid @RequestBody Personaje personaje) {
        Personaje newpersonaje = personajeService.savePersonaje(personaje);
        return ResponseEntity.ok(newpersonaje);
    }

    /*
     * --------obtengo la lista de personajes
     * 
     */
    @GetMapping("/personajes")
    public ResponseEntity<Map<String, String>> getAllPersonajes() {
        try {
            // Retrieve the map from the service
            Map<String, String> personajes = personajeService.getAllPersonajes();

            if (personajes.isEmpty()) {
                // Return 404 Not Found if no personajes are available
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "La lista esta vacia"));
            }

            // Return the map wrapped in a ResponseEntity with a 200 OK status
            return ResponseEntity.ok(personajes);
        } catch (Exception e) {
            // Return a 500 Internal Server Error in case of an unexpected exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Un error ha ocurrido: " + e.getMessage()));
        }
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
     * @Valid para forzar la validación de ese objeto de acuerdo a las
     * anotaciones que previamente hemos añadido en la definición de dicha clase
     */
    @PutMapping("/personajes/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@Valid @PathVariable Long id, @RequestBody Personaje personaje) {
        Personaje updatedpersonaje = personajeService.updatePersonaje(id, personaje);
        return ResponseEntity.ok(updatedpersonaje);

    }

    /*
     * borrar un producto por id
     */
    @DeleteMapping("/personajes/{id}")
    public ResponseEntity<Map<String, String>> deletePersonaje(@PathVariable Long id) {
        boolean borrarPersonaje = personajeService.deletePersonaje(id);

        if (borrarPersonaje) {
            // Return a success message if the Personaje was deleted
            return ResponseEntity.ok(Map.of("message", "Personaje borrado con éxito"));
        } else {
            // Return a 404 error if the Personaje was not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "El personaje con ID " + id + " no existe"));
        }
    }

}
