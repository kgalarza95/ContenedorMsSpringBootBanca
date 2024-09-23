package com.test.banco.cliente.controller;

import com.test.banco.cliente.dto.ClienteInDto;
import com.test.banco.cliente.service.ClienteService;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{clienteid}")
    public ResponseEntity<?> getCliente(@PathVariable Long clienteid) {
        return new ResponseEntity<>(clienteService.getClienteById(clienteid), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getClienteAll() {
        return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteInDto cliente) {
        System.out.println("cliente   controller " + cliente.toString());
        return new ResponseEntity<>(clienteService.createCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{clienteid}")
    public ResponseEntity<?> updateCliente(@PathVariable Long clienteid, @Valid @RequestBody ClienteInDto cliente) {
        return new ResponseEntity<>(clienteService.updateCliente(clienteid, cliente), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateParcialCliente(@RequestBody ClienteInDto cliente) {
        return new ResponseEntity<>(clienteService.updateCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{clienteid}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long clienteid) {
        clienteService.deleteCliente(clienteid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
