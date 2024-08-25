package com.rickyandmorti.rickyandmorti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Data: Genera automáticamente los métodos getters, setters, toString(),
 *        equals(), y hashCode() para todos los campos de la clase.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String descripcion;
    private String raza;
    private LocalDate fechaCreacion;

    
    public static String capitalizeFirstLetterNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre; // Maneja el caso de cadenas nulas o vacías
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }
}