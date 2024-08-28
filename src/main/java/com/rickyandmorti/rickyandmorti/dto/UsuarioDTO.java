package com.rickyandmorti.rickyandmorti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * @Data: Genera automáticamente los métodos getters, setters, toString(),
 *        equals(), y hashCode() para todos los campos de la clase.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
///------------------los campos aqui añadidos aunque no los saque obtenga en el service en el postman sale igual 
// por lo tanto debería quitarlos para buenas prácticas
public class UsuarioDTO {
    @JsonIgnore
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String email;
    private String telefono;
    //este campo no se incluira en la salida del json
   // @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDateTime fechaCreacion;
    private DireccionDTO direccion;
    @JsonIgnore
    private boolean softdeletedUser;

  
}

