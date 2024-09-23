package com.test.banco.cliente.dto;


import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Data
public class ClienteInOutDto {

    private String nombreCliente;
    private String generoCliente;
    private int edadCliente;
    private String identificacionCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private Long clienteidCliente;
    private String contrasenaCliente;
    private boolean estadoCliente;
}
