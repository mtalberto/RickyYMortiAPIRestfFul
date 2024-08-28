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
    public Map<String, UsuarioDTO> getAllUsuarios() {
        // Obtiene la lista de usuarios
        List<Usuario> listUsuarios = usuarioRepository.findAllActiveUsers();

        // Convierte la lista en un mapa
        Map<String, UsuarioDTO> usuariosMap = listUsuarios.stream()
                .collect(Collectors.toMap(
                        usuario -> usuario.getId().toString(), // Clave: ID de Usuario
                        usuario -> UsuarioDTO.builder()
                                .id(usuario.getId())
                                .nombre(usuario.getNombre())
                                .apellido(usuario.getApellido())
                                .edad(usuario.getEdad())
                                .password(usuario.getPassword())
                                .fechaNacimiento(usuario.getFechaNacimiento())
                                .genero(usuario.getGenero())
                                .fechaCreacion(usuario.getFechaCreacion())
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
     * obtengo el usuario por el id
     */
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByActiveUser(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .password(usuario.getPassword())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .edad(usuario.getEdad())
                    .fechaNacimiento(usuario.getFechaNacimiento())
                    .genero(usuario.getGenero())
                    .fechaCreacion(usuario.getFechaCreacion())
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
     * obtengo el usuario por el email
     */
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioByEmail(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailActiveUser(email);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .edad(usuario.getEdad())
                    .fechaNacimiento(usuario.getFechaNacimiento())
                    .genero(usuario.getGenero())
                    .fechaCreacion(usuario.getFechaCreacion())
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
        Optional<Usuario> existingUsuario = usuarioRepository.findByActiveUser(id);
        if (existingUsuario.isPresent()) {
            Usuario usuario = existingUsuario.get();
            usuario.setNombre(updateUsuario.getNombre());
            usuario.setEdad(updateUsuario.getEdad());
            usuario.setApellido(updateUsuario.getApellido());
            usuario.setPassword(updateUsuario.getPassword());
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
        Optional<Usuario> usuarioOpt = usuarioRepository.findByActiveUser(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            usuarioRepository.delete(usuario);
            return true;
        } else {

            return false;
        }
    }
}
