package com.test.banco.cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author kgalarza
 */
@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "clienteid"))
@Data
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long clienteid;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    private String contrasena;
    private boolean estado;

}
