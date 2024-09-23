
package com.test.banco.cliente.exception;

/**
 *
 * @author kgalarza
 */
public class ValidacionGeneralCtasException extends RuntimeException {

    private String error = "Lo sentimos, se ha presentado un problema interno.";

    public ValidacionGeneralCtasException() {
    }

    public ValidacionGeneralCtasException(String mensaje) {
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
