package com.rickyandmorti.rickyandmorti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Data: Genera automáticamente los métodos getters, setters, toString(),
 *        equals(), y hashCode() para todos los campos de la clase.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String email;
    private String telefono;
    private String password;
    private LocalDateTime fechaCreacion;
    private DireccionDTO direccion;
    private boolean softdeletedUser;

  
}

