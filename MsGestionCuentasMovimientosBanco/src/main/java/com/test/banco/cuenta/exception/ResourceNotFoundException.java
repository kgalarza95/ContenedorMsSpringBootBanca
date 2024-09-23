
package com.test.banco.cuenta.exception;

/**
 *
 * @author kgalarza
 */
public class ResourceNotFoundException extends RuntimeException {

    private String mensajeErrorDefecto = "Recurso no econtrado";

    public ResourceNotFoundException() {
        super("Recurso no econtrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable causa) {
        super(causa);
    }

    public String getMensajeErrorDefecto() {
        return mensajeErrorDefecto;
    }

    public void setMensajeErrorDefecto(String mensajeErrorDefecto) {
        this.mensajeErrorDefecto = mensajeErrorDefecto;
    }

}
