package com.rickyandmorti.rickyandmorti.repository;

import com.rickyandmorti.rickyandmorti.entitys.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// especifica que es un repositorio y spring creara una instancia de el automaticamente
//interfaz jpa proporciona operaciones CRUD(crear,leer,actualizar, eliminar)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     @Query("SELECT u FROM Usuario u WHERE u.softdeletedUser = false")
    List<Usuario> findAllActiveUsers();

     @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.softdeletedUser = false")
    Optional<Usuario> findByActiveUser(@Param("id") Long id);
   
}
