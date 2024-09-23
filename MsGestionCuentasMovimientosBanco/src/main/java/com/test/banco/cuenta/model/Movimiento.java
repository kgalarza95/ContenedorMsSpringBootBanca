package com.test.banco.cuenta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author kgalarza
 */
@Entity
@Table(name = "movimientos")
@Data
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @LastModifiedDate
    private LocalDateTime fechaMovimiento;
    private double saldoInicial;
    private double valorMovimiento;
    private double saldoDisponible;
    private String descripcionMovimiento;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
