
package com.test.banco.cuenta.model;

import jakarta.persistence.Column;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author kgalarza
 */
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona extends Auditoria {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String nombre;
    private String genero;
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 80, message = "La edad no puede ser mayor de 80 años")
    private int edad;

    @NotNull(message = "La identificación no puede ser nula")
    @Pattern(regexp = "^[0-9]{10,13}$", message = "La identificación debe ser numérica y tener entre 10 y 13 dígitos")
    @Column(nullable = false, unique = true)
    private String identificacion;
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccion;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe ser un número válido y tener entre 10 y 15 dígitos")
    private String telefono;

}
