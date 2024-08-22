package com.rickyandmorti.rickyandmorti.controladores;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickyandmorti.rickyandmorti.dto.PersonajeDTO;
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
    public ResponseEntity<Object> savePersonaje(@Valid @RequestBody Personaje personaje) {
        // Verifica si el nombre del personaje ya existe
        Optional<String> personajeOPT = personajeService.getNamePersonaje(personaje.getNombre());

        if (personajeOPT.isPresent()) {
            // Si el personaje ya existe, devuelve un error 409 Conflict
            throw new ResourceNotFoundException("El nombre"+ personaje.getNombre() +"del personaje ya existe");
        }

        // Si el personaje no existe, lo guarda
        Personaje newPersonaje = personajeService.savePersonaje(personaje);

        // Devuelve el nuevo personaje creado con estado 200 OK
        return ResponseEntity.ok(newPersonaje);
    }

    /*
     * --------obtengo la lista de personajes
     * podria hacer 2 metodos que controle los errores de not found y serval error
     */
    @GetMapping("/personajes")
    public ResponseEntity<Map<String, Object>> getAllPersonajes() {

        Map<String, PersonajeDTO> personajesMap = personajeService.getAllPersonajes();

        if (personajesMap.isEmpty()) {
            // Return 404 Not Found if no personajes are available
            throw new ResourceNotFoundException("No se encontraron personajes");

        }

        return ResponseEntity.ok(Map.of("usuarios", personajesMap));

    }

    /*
     * get a personaje por ID
     */
    @GetMapping("/personajes/{id}")
    public ResponseEntity<Map<String, Object>> getPersonajeById(@PathVariable Long id) {
        Optional<PersonajeDTO> personajeDTO = personajeService.getPersonajeById(id);

        Map<String, Object> response = new HashMap<>();
        if (personajeDTO.isPresent()) {
            response.put("personaje", personajeDTO.get());
            return ResponseEntity.ok(response);
            // mapping
        } else {
            throw new ResourceNotFoundException("el personaje con el " + id + " no existe ");
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
    public ResponseEntity<Personaje> updatePersonaje(@Valid @PathVariable Long id,
            @Valid @RequestBody Personaje personaje) {
        Optional<PersonajeDTO> personajeDTO = personajeService.getPersonajeById(id);

        if (personajeDTO.isEmpty()) {
            throw new ResourceNotFoundException("el personaje con el " + id + " no existe ");
        } else {

            Personaje updatedpersonaje = personajeService.updatePersonaje(id, personaje);
            return ResponseEntity.ok(updatedpersonaje);
        }

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
            throw new ResourceNotFoundException("el personaje con el " + id + " no existe ");
        }
    }

}
