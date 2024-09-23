package com.test.banco.cuenta.controller;

import com.test.banco.cuenta.dto.MovimientoInDto;
import com.test.banco.cuenta.dto.MovimientoOutDto;
import com.test.banco.cuenta.service.MovimientoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kgalarza
 */
@RestController
@RequestMapping("/v1/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/{numMovimiento}")
    public ResponseEntity<?> findById(@PathVariable Long numMovimiento) {
        MovimientoOutDto movimientoDto = movimientoService.findById(numMovimiento);
        return new ResponseEntity<>(movimientoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoOutDto>> findAllMovimientos() {
        List<MovimientoOutDto> movimiento = movimientoService.findAllMovimientos();
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovimientoOutDto> generarMovimineto(@Valid @RequestBody MovimientoInDto movimiento) {
        MovimientoOutDto createdCuenta = movimientoService.generarMovimineto(movimiento);
        return new ResponseEntity<>(createdCuenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoOutDto> updateMovimiento(@Valid @RequestBody MovimientoInDto movimiento) {
        MovimientoOutDto updatedCuenta = movimientoService.updateMovimiento(movimiento);
        return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovimientoOutDto> updateParcialMovimiento(@RequestBody MovimientoInDto movimiento) {
        MovimientoOutDto updatedCuenta = movimientoService.updateParcialMovimiento(movimiento);
        return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
