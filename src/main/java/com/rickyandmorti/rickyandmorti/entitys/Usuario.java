package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "usuarios")
public class Usuario extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "Email debe ser válido")
    @Size(max = 100, message = "Email no debe tener más de 100 caracteres")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email debe ser válido ejemplo@gmail.com")
    private String email;

    //incluir codigo de paises
    @Column(name = "telefono")
    @Size(max =20, message = "telefono no debe tener más de 20 caracteres")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Teléfono solo incluir números")
    private String telefono;
}
