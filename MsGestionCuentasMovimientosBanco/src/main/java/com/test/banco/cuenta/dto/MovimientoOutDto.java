package com.test.banco.cuenta.dto;

import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class MovimientoOutDto {

    private double saldoInicial;
    private double valorMovimiento;
    private double saldoDisponible;
    private String descripcionMovimiento;
    private Long idCuenta;
}
