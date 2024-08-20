package com.rickyandmorti.rickyandmorti.repository;

import com.rickyandmorti.rickyandmorti.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// especifica que es un repositorio y spring creara una instancia de el automaticamente
//interfaz jpa proporciona operaciones CRUD(crear,leer,actualizar, eliminar)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
}
