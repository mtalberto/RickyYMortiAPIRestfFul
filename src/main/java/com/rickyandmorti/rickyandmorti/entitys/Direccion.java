package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Se utiliza para marcar una clase como una entidad embebible, lo que significa
 * que sus propiedades se pueden "incrustar" dentro de otra entidad sin crear
 * una tabla separada en la base de datos
 * 
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {

    
    @NotBlank(message="el campo calle no puede estar vacio")
    private String calle;
    @NotBlank(message = "el campo ciudad no puede estar vacio")
    private String ciudad;
    @NotBlank(message = "el campo codigoPostal no puede estar vacio")
    @Size(max=5, message = "El código postal debe tener 5 caracteres")
    @Pattern(regexp = "^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$", message = "El código postal no es válido")
    private String codigoPostal;
    @NotBlank(message = "el campono pais no puede estar vacio")
    private String pais;

   
}