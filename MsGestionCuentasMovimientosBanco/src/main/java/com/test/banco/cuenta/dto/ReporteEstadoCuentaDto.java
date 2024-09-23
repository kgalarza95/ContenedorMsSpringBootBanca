package com.test.banco.cuenta.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class ReporteEstadoCuentaDto {

    private LocalDateTime fechaMovimiento;
    private String nombreCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private double valorMovimiento;
    private double saldoDisponible;
    private String descripcionMovimiento;
    private Boolean estadoCuenta;

    public ReporteEstadoCuentaDto(LocalDateTime fechaMovimiento, String nombreCliente, String numeroCuenta,
            String tipoCuenta, double saldoInicial, double valorMovimiento,
            double saldoDisponible, String descripcionMovimiento, Boolean estadoCuenta) {
        this.fechaMovimiento = fechaMovimiento;
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.valorMovimiento = valorMovimiento;
        this.saldoDisponible = saldoDisponible;
        this.descripcionMovimiento = descripcionMovimiento;
        this.estadoCuenta = estadoCuenta;
    }
}
