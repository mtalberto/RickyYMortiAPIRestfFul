package com.rickyandmorti.rickyandmorti.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rickyandmorti.rickyandmorti.dto.DireccionDTO;
import com.rickyandmorti.rickyandmorti.dto.UsuarioDTO;
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
     * builder
     * obtengo todo los usuarios
     */
    @Transactional(readOnly = true)
    public Map<String, UsuarioDTO> getAlUsuarios() {
        // Obtiene la lista de usuarios
        List<Usuario> listUsuarios = usuarioRepository.findAll();

        // Convierte la lista en un mapa
        Map<String, UsuarioDTO> usuariosMap = listUsuarios.stream()
                .collect(Collectors.toMap(
                        usuario -> usuario.getId().toString(), // Clave: ID de Usuario
                        usuario -> UsuarioDTO.builder()
                                .id(usuario.getId())
                                .nombre(usuario.getNombre())
                                .apellido(usuario.getApellido())
                                .edad(usuario.getEdad())
                                .fechaNacimiento(usuario.getFechaNacimiento())
                                .genero(usuario.getGenero())
                                .email(usuario.getEmail())
                                .telefono(usuario.getTelefono())
                                .direccion(DireccionDTO.builder()
                                        .calle(usuario.getDireccion().getCalle())
                                        .ciudad(usuario.getDireccion().getCiudad())
                                        .codigoPostal(usuario.getDireccion().getCodigoPostal())
                                        .pais(usuario.getDireccion().getPais())
                                        .build())
                                .build()));

        return usuariosMap;
    }

    /*
     * obtengo el id del usuario
     */
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .edad(usuario.getEdad())
                    .fechaNacimiento(usuario.getFechaNacimiento())
                    .genero(usuario.getGenero())
                    .email(usuario.getEmail())
                    .telefono(usuario.getTelefono())
                    .direccion(DireccionDTO.builder()
                            .calle(usuario.getDireccion().getCalle())
                            .ciudad(usuario.getDireccion().getCiudad())
                            .codigoPostal(usuario.getDireccion().getCodigoPostal())
                            .pais(usuario.getDireccion().getPais()).build())
                    .build();
            return Optional.of(usuarioDTO);
        } else {
            return Optional.empty();
        }
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
