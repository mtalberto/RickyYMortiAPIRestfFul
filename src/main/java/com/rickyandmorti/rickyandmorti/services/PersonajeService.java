package com.rickyandmorti.rickyandmorti.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rickyandmorti.rickyandmorti.dto.PersonajeDTO;
import com.rickyandmorti.rickyandmorti.entitys.Personaje;
import com.rickyandmorti.rickyandmorti.repository.PersonajesRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//servive para manejar la logica empresarial de la API REST
@Service
public class PersonajeService {

    private final PersonajesRepository personajesRepository;

    // se puede omitir si solo tiene un constructor @Autowired

    public PersonajeService(PersonajesRepository personajesRepository) {

        this.personajesRepository = personajesRepository;
    }
    /*
     * guargar un personaje
     */

    @Transactional
    public Personaje savePersonaje(Personaje personaje) {

        return personajesRepository.save(personaje);

    }

    @Transactional(readOnly = true)
    public Optional<String> getNamePersonaje(String nombre) {
        Optional<Personaje> personaje = personajesRepository.findByNombre(nombre);

       
        return personaje.map(Personaje::getNombre);
    }

    /*
     * obtengo todo los personajes
     */
    @Transactional(readOnly = true)
    public Map<String, PersonajeDTO> getAllPersonajes() {
        // Obtiene la lista de personajes
        List<Personaje> listPersonajes = personajesRepository.findAll();

        // Convierte la lista en un mapa
        Map<String, PersonajeDTO> personajesMap = listPersonajes.stream()
                .collect(Collectors.toMap(
                        personaje -> personaje.getId().toString(),
                        personaje -> PersonajeDTO.builder()
                                .id(personaje.getId())
                                .nombre(personaje.getNombre())
                                .apellido(personaje.getApellido())
                                .edad(personaje.getEdad())
                                .fechaNacimiento(personaje.getFechaNacimiento())
                                .genero(personaje.getGenero())
                                .descripcion(personaje.getDescripcion())
                                .raza(personaje.getRaza())
                                .fechaCreacion(personaje.getFechaCreacion())
                                .build()));

        return personajesMap;
    }

    /*
     * obtengo el id del personaje
     */
    @Transactional(readOnly = true)
    public Optional<PersonajeDTO> getPersonajeById(Long id) {
        Optional<Personaje> personajeOpt = personajesRepository.findById(id);
        if (personajeOpt.isPresent()) {
            Personaje personaje = personajeOpt.get();
            PersonajeDTO personajeDTO = PersonajeDTO.builder()
                    .id(personaje.getId())
                    .nombre(personaje.getNombre())
                    .apellido(personaje.getApellido())
                    .edad(personaje.getEdad())
                    .fechaNacimiento(personaje.getFechaNacimiento())
                    .genero(personaje.getGenero())
                    .descripcion(personaje.getDescripcion())
                    .raza(personaje.getRaza())
                    .fechaCreacion(personaje.getFechaCreacion())
                    .build();
            return Optional.of(personajeDTO);
        } else {
            return Optional.empty();
        }
    }

    /*
     * update personajes
     */
    @Transactional
    public Personaje updatePersonaje(Long id, Personaje updatePersonaje) {
        Optional<Personaje> existingPersonaje = personajesRepository.findById(id);
        if (existingPersonaje.isPresent()) {
            Personaje personaje = existingPersonaje.get();
            personaje.setNombre(updatePersonaje.getNombre());
            personaje.setEdad(updatePersonaje.getEdad());
            personaje.setDescripcion(updatePersonaje.getDescripcion());
            personaje.setApellido(updatePersonaje.getApellido());
            personaje.setRaza(updatePersonaje.getRaza());
            personaje.setGenero(updatePersonaje.getGenero());
            personaje.setFechaNacimiento(updatePersonaje.getFechaNacimiento());

            return personajesRepository.save(personaje);
        } else {
            throw new RuntimeException("No puedo actualizar el personaje" + id);
        }
    }

    /*
     * borrar personaje
     */
    @Transactional
    public boolean deletePersonaje(Long id) {
        if (personajesRepository.existsById(id)) {
            personajesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
