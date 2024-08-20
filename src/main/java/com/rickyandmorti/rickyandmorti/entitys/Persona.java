package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

//MappedSuperclass Jpa Herencia persona no tendra tabla
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Persona {

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 20, message = "Nombre no debe tener más de 20 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Apellido no puede estar vacío")
    @Size(max = 20, message = "Apellido no debe tener más de 20 caracteres")
    @Column(nullable = false)
    private String apellido;

    @Size(max = 3, message = "Nombre no debe tener más de 3 caracteres")
    @Column(nullable = false)
    private int edad;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private LocalDate date;

    @NotBlank(message = "genero no puede estar vacío")
    @Size(max = 10, message = "genero no debe tener más de 10 caracteres")
    @Column(nullable = false)
    private String genero;

}