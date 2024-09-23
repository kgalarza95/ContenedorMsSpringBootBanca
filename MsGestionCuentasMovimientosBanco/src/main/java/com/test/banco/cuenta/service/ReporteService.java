package com.test.banco.cuenta.service;

import com.test.banco.cuenta.dto.ReporteEstadoCuentaDto;
import com.test.banco.cuenta.repository.CuentaRepository;
import com.test.banco.cuenta.repository.MovimientoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kgalarza
 */
@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<ReporteEstadoCuentaDto> generarReporteEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
        LocalDateTime fechaFinDateTime = fechaFin.atTime(LocalTime.MAX);
        return movimientoRepository.obtenerReporteEstadoCuenta(fechaInicioDateTime, fechaFinDateTime);
    }

    public List<ReporteEstadoCuentaDto> generarReporteEstadoCuenta() {
        return movimientoRepository.obtenerReporteEstadoCuenta();
    }
}
