package com.test.banco.cuenta.repository;

import com.test.banco.cuenta.dto.ReporteEstadoCuentaDto;
import com.test.banco.cuenta.model.Movimiento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kgalarza
 */
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT new com.test.banco.cuenta.dto.ReporteEstadoCuentaDto(mv.fechaMovimiento, cl.nombre, cta.numeroCuenta, cta.tipoCuenta, mv.saldoInicial, mv.valorMovimiento, mv.saldoDisponible, mv.descripcionMovimiento, cta.estado) "
            + "FROM Cliente cl "
            + "JOIN Cuenta cta ON cl.clienteid = cta.clienteid "
            + "JOIN Movimiento mv ON mv.cuenta.id = cta.id "
            + "WHERE 1 = 1"
            + "AND mv.fechaMovimiento BETWEEN :fechaInicio AND :fechaFin "
            + "AND cta.estado = true "
            + "ORDER BY mv.fechaMovimiento, cta.numeroCuenta")
    List<ReporteEstadoCuentaDto> obtenerReporteEstadoCuenta(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT new com.test.banco.cuenta.dto.ReporteEstadoCuentaDto(mv.fechaMovimiento, cl.nombre, cta.numeroCuenta, cta.tipoCuenta, mv.saldoInicial, mv.valorMovimiento, mv.saldoDisponible, mv.descripcionMovimiento, cta.estado) "
            + "FROM Cliente cl "
            + "JOIN Cuenta cta ON cl.clienteid = cta.clienteid "
            + "JOIN Movimiento mv ON mv.cuenta.id = cta.id "
            + "ORDER BY mv.fechaMovimiento, cta.numeroCuenta")
    List<ReporteEstadoCuentaDto> obtenerReporteEstadoCuenta();
}
