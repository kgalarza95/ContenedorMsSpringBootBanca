
package com.test.banco.cuenta.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 *
 * @author kgalarza
 */
@Entity
@Table(name = "cuentas")
@Data
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String numeroCuenta;
    @Column(nullable = false)
    private String tipoCuenta;
    private double saldoEnLinea;
    private boolean estado;
    private Long clienteid;

    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;
}
