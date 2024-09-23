package com.test.banco.cuenta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class CuentaInDto extends CampoEntradaInDto {

    private Long id;
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    private String numeroCuenta;
    @NotBlank(message = "El tipo de cuenta no puede estar vacío")
    private String tipoCuenta;
    @NotNull(message = "El saldo inicial no puede ser nulo")
    private double saldoEnLinea;
    private boolean estado;
    @Schema(description = "Identificador único del cliente asociado a la cuenta")
    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Long clienteid;

}
