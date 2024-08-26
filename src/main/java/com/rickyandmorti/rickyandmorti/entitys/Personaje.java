package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "personajes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Personaje extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "descripcion no puede estar vacio")
    @Column(name = "descripcion",nullable = false)
    @Size(max = 50)
    private String descripcion;

    @NotBlank(message = "raza no puede estar vacio")
    @Column(name = "raza", nullable = false)
    @Size(max = 50)
    private String raza;

   

}
