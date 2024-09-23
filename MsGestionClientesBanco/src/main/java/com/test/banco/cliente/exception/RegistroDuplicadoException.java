
package com.test.banco.cliente.exception;

/**
 *
 * @author kgalarza
 */
public class RegistroDuplicadoException extends RuntimeException {

    private String error = "Registro duplicado";

    public RegistroDuplicadoException() {
    }

    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
        this.error = mensaje;
    }

    @Override
    public String toString() {
        return error;
    }

    @Override
    public String getMessage() {
        return error;
    }
}
