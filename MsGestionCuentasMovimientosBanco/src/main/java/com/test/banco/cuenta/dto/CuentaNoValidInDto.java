package com.test.banco.cuenta.dto;

import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class CuentaNoValidInDto extends CampoEntradaInDto {

    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoEnLinea;
    private boolean estado;
    private Long clienteid;

}
