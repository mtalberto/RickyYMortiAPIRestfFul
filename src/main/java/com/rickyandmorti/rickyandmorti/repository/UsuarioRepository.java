package com.rickyandmorti.rickyandmorti.repository;

import com.rickyandmorti.rickyandmorti.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// especifica que es un repositorio y spring creara una instancia de el automaticamente
//interfaz jpa proporciona operaciones CRUD(crear,leer,actualizar, eliminar)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //@modifying para que Hibernate sepa que se trata de una operación de
    // modificación
    @Modifying
    @Query("UPDATE Usuario u SET u.softdeletedUser = true WHERE u.id = :id")
    void softDeleteById(@Param("id") Long id);
}
