package com.test.banco.cliente.exception;

import com.test.banco.cliente.dto.RespuestaOutDto;
import jakarta.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author kgalarza
 */
@ControllerAdvice
public class ManejadorGenException {

    private static final Logger logger = LoggerFactory.getLogger(ManejadorGenException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaOutDto> globalExceptionHandler(Exception ex) {
        String mensajeUsuario = "Lo sentimos, ha ocurrido un error inesperado. Por favor, intenta nuevamente más tarde.";
        logger.error("Error inesperado: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new RespuestaOutDto(HttpStatus.INTERNAL_SERVER_ERROR, mensajeUsuario, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RespuestaOutDto> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());
        return new ResponseEntity<>(new RespuestaOutDto(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RespuestaOutDto> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        logger.warn("Intento de registrar un duplicado", ex.getMessage());
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RespuestaOutDto(estado, "No se permiten registros duplicados", ex.getMessage()), estado);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<RespuestaOutDto> RegistroDuplicadoExceptionHandler(RegistroDuplicadoException ex) {
        logger.warn("Intento de registrar un duplicado", ex.getMessage());
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RespuestaOutDto(estado, ex.getMessage(), ex.getMessage()), estado);
    }

    @ExceptionHandler(ValidacionGeneralCtasException.class)
    public ResponseEntity<RespuestaOutDto> ValidacionGeneralCtasException(ValidacionGeneralCtasException ex) {
        logger.warn("Validación general de cuentas", ex.getMessage());
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RespuestaOutDto(estado, ex.getMessage(), ex.getMessage()), estado);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaOutDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        String mensajeUsuario = "Error de validación en los datos enviados.";
        logger.info("Error de validación: {}", errores);
        return new ResponseEntity<>(new RespuestaOutDto(HttpStatus.BAD_REQUEST, mensajeUsuario, errores.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespuestaOutDto> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("El valor '%s' no es válido para el campo '%s'. Se esperaba un número.",
                ex.getValue(), ex.getName());
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RespuestaOutDto(estado, errorMessage, ex.getMessage()), estado);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RespuestaOutDto> handleConstraintViolationException(ConstraintViolationException ex) {
        String mensajeUsuario = "Error de validación en uno o más campos.";
        logger.info("Error de constraint violation: {}", ex.getConstraintViolations());
        return new ResponseEntity<>(new RespuestaOutDto(HttpStatus.BAD_REQUEST, mensajeUsuario, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RespuestaOutDto> handleAccessDeniedException(AccessDeniedException ex) {
        String mensajeUsuario = "No tienes permisos para realizar esta acción.";
        logger.warn("Acceso denegado: {}", ex.getMessage());
        return new ResponseEntity<>(new RespuestaOutDto(HttpStatus.FORBIDDEN, mensajeUsuario, ex.getMessage()), HttpStatus.FORBIDDEN);
    }

}
