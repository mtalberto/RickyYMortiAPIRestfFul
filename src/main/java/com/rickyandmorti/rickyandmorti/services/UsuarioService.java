package com.rickyandmorti.rickyandmorti.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rickyandmorti.rickyandmorti.entitys.Usuario;
import com.rickyandmorti.rickyandmorti.repository.UsuarioRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*
     * guargar un usuario
     */

    @Transactional
    public Usuario savesUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }

    /*
     * obtengo todo los usuarios
     */
    @Transactional(readOnly = true)
    public Map<String, String> getAlUsuarios() {
        // obtengo la lista de usuarios
        List<Usuario> Listusuarios = usuarioRepository.findAll();

        // convierto la lista en un map
        Map<String, String> personajesMap = Listusuarios.stream()
                .collect(Collectors.toMap(
                        personaje -> personaje.getId().toString(), // usuari ID
                        personaje -> personaje.toString() //
                ));

        return personajesMap;
    }

    /*
     * obtengo el id del usuario
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    /*
     * update usuario
     */
    @Transactional
    public Usuario updateUsuario(Long id, Usuario updateUsuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario usuario = existingUsuario.get();
            usuario.setNombre(updateUsuario.getNombre());
            usuario.setEdad(updateUsuario.getEdad());
            usuario.setApellido(updateUsuario.getApellido());
            usuario.setGenero(updateUsuario.getGenero());
            usuario.setFechaNacimiento(updateUsuario.getFechaNacimiento());
            usuario.setEmail(updateUsuario.getEmail());
            usuario.setTelefono(updateUsuario.getTelefono());

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("no puedo actualizar el usuario" + id);
        }
    }

    /*
     * borrar usuario
     */
    @Transactional
    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
