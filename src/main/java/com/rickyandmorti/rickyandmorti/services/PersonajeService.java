package com.rickyandmorti.rickyandmorti.services;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    guargar un personaje
     */

    @Transactional
    public Personaje savePersonajes(Personaje personaje) {

        return personajesRepository.save(personaje);

    }

   /*
    obtengo todo los personajes
   */
     @Transactional(readOnly = true)
    public Map<String, String> getAllPersonajes() {
        // obtengo la lista de personajes
        List<Personaje> Listpersonajes = personajesRepository.findAll();
        
        // convierto la lista en un map
        Map<String, String> personajesMap = Listpersonajes.stream()
                .collect(Collectors.toMap(
                        personaje -> personaje.getId().toString(), // Key: Personaje ID
                        personaje -> personaje.toString()           // Value: String representation of Personaje
                ));

        return personajesMap;
    }

    /*
    obtengo el id del personaje
    */
    @Transactional(readOnly = true)
    public Optional<Personaje> getPersonajeById(Long id) {
        return personajesRepository.findById(id);
    }

    /*
    update personajes
    */
    @Transactional
    public Personaje updatePersonaje(Long id,Personaje updatePersonaje){
        Optional<Personaje> existingPersonaje= personajesRepository.findById(id);
        if(existingPersonaje.isPresent()){
            Personaje personaje= existingPersonaje.get();
            personaje.setNombre(updatePersonaje.getNombre());
            personaje.setDescripcion(updatePersonaje.getDescripcion());
            personaje.setLastName(updatePersonaje.getLastName());
            personaje.setEmail(personaje.getEmail());
            return personajesRepository.save(personaje);
        }else{
            throw new RuntimeException("Cannot update personaje"+id);
        }
    }


    /*
    borrar personaje
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
