package com.test.banco.cliente.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class ClienteInDto extends CampoEntradaInDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombreCliente;
    private String generoCliente;
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 80, message = "La edad no puede ser mayor de 80 años")
    private int edadCliente;
    @NotNull(message = "La identificación no puede ser nula")
    @Pattern(regexp = "^[0-9]{10,13}$", message = "La identificación debe ser numérica y tener entre 10 y 13 dígitos")
    private String identificacionCliente;
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccionCliente;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe ser un número válido y tener entre 10 y 15 dígitos")
    private String telefonoCliente;
    private Long clienteidCliente;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    private String contrasenaCliente;
    private boolean estadoCliente;
}
