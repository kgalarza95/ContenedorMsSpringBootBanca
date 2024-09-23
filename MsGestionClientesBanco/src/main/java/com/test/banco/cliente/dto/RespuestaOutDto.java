package com.test.banco.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author kgalarza
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaOutDto {

    private HttpStatus codEstado;
    private String mensajeUsuario;
    private String mensajeTecnico;
}
