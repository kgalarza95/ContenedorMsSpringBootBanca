package com.test.banco.cuenta.controller;

import com.test.banco.cuenta.dto.CuentaInDto;
import com.test.banco.cuenta.dto.CuentaNoValidInDto;
import com.test.banco.cuenta.dto.CuentaOutDto;
import com.test.banco.cuenta.service.CuentaService;
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
@RequestMapping("/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{numCuenta}")
    public ResponseEntity<?> getCuenta(@PathVariable Long numCuenta) {
        CuentaOutDto cuentaDto = cuentaService.findById(numCuenta);
        return new ResponseEntity<>(cuentaDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CuentaOutDto>> getCuentasAll() {
        List<CuentaOutDto> cuentas = cuentaService.findAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CuentaOutDto> createCuenta(@Valid @RequestBody CuentaInDto cuenta) {
        CuentaOutDto createdCuenta = cuentaService.createCuenta(cuenta);
        return new ResponseEntity<>(createdCuenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaOutDto> updateCuenta(@Valid @RequestBody CuentaInDto cuenta) {
        CuentaOutDto updatedCuenta = cuentaService.updateCuenta(cuenta);
        return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CuentaOutDto> updateParcialCuenta(@RequestBody CuentaNoValidInDto cuenta) {
        CuentaOutDto updatedCuenta = cuentaService.updateParcialCuenta(cuenta);
        return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
