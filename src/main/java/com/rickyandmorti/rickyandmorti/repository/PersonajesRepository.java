package com.rickyandmorti.rickyandmorti.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.rickyandmorti.rickyandmorti.entitys.Personaje;


// especifica que es un repositorio y spring creara una instancia de el automaticamente
//interfaz jpa proporciona operaciones CRUD(crear,leer,actualizar, eliminar)
@Repository
public interface PersonajesRepository extends JpaRepository<Personaje,Long> {
        //esto general SELECT * FROM Usuario WHERE email = ?;
        Optional<Personaje> findByNombre(String nombre);

   /** es lo mismo qu lo de arria
    * @Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
    * Optional<Personaje> findByNombre(@Param("nombre") String nombre);
    */   
    
    
   

}
