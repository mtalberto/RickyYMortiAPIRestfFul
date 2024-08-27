package com.rickyandmorti.rickyandmorti.entitys;



import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
// soft delete actualizo el campo
@SQLDelete(sql = "UPDATE usuarios SET softdeleted_user = true WHERE id=?")
//este filtro excluiran a los usuarios eliminados
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")
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
    @Size(max =20,min = 12, message = "telefono no debe tener entre 12 y 20 caracteres")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Teléfono solo incluir números")
    private String telefono;

    @Column(name = "softdeleted_user")  
    private boolean softdeletedUser = Boolean.FALSE;

    @Column(name = "password")
    @NotBlank(message = "password no puede estar vacío")
    @Size(min = 10, message = "password  debe tener minimo de 10 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-?¿ºª<>.,`+ç´!@#$%^&*()]).*$", message = "La contraseña debe tener al menos 10 caracteres, incluyendo un número, una letra mayúscula, una letra minúscula y un carácter especial")
    private String password;

  
    /**
     * Se utiliza para marcar una clase como una entidad embebible, lo que significa
     * que sus propiedades se pueden "incrustar" dentro de otra entidad sin crear
     * una tabla separada en la base de datos
     * 
     * para que que las validaciones de direccion se aplique el campo Direccion
     * debe estar anotado con @Valid
     */

    @Embedded
    @Valid
    private Direccion direccion;


    
}

