package com.test.banco.cliente.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kgalarza
 */
public class ClienteTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testClienteValidacion() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Pérez");
        cliente.setGenero("M");
        cliente.setEdad(25);
        cliente.setIdentificacion("1234567890");
        cliente.setContrasena("password123");
        cliente.setEstado(true);
        cliente.setTelefono("0987654321");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

        assertTrue(violations.isEmpty(), "Debe pasar todas las validaciones");
    }

    @Test
    void testClienteConErrores() {
        Cliente cliente = new Cliente();
        cliente.setNombre("");
        cliente.setEdad(-1);
        cliente.setIdentificacion("1234");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

        assertFalse(violations.isEmpty(), "Debe tener errores de validación");

    }
}
