package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//MappedSuperclass Jpa Herencia persona no tendra tabla
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Persona {

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 20, message = "Nombre no debe tener más de 20 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Apellido no puede estar vacío")
    @Size(max = 20, message = "Apellido no debe tener más de 20 caracteres")
    @Column(nullable = false)
    private String apellido;

    //size no es compatible para int
    @Min(value = 0,  message = "La edad no puede ser menor que 0")
    @Max(value = 150, message = "La edad no puede ser mayor que 150")
    @Column(nullable = false)
    private int edad;

    /*
     * // @Temporal: Para especificar cómo se almacenan las fechas en la base de
     * datos
     * // con JPA.
     * @JsonFormat para el formate de la fecha en JSOn
     */
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "genero no puede estar vacío")
    @Size(max = 10, message = "genero no debe tener más de 10 caracteres")
    @Column(nullable = false)
    private String genero;

}