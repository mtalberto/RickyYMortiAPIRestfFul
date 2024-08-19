package com.rickyandmorti.rickyandmorti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rickyandmorti.rickyandmorti.entitys.Personaje;

// especifica que es un repositorio y spring creara una instancia de el automaticamente
//interfaz jpa proporciona operaciones CRUD(crear,leer,actualizar, eliminar)
@Repository
public interface PersonajesRepository extends JpaRepository<Personaje,Long> {

   

}
