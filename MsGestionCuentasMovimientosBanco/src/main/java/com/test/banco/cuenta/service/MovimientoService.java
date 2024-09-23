package com.test.banco.cuenta.service;

import com.test.banco.cuenta.dto.MovimientoInDto;
import com.test.banco.cuenta.dto.MovimientoOutDto;
import com.test.banco.cuenta.exception.ResourceNotFoundException;
import com.test.banco.cuenta.exception.ValidacionGeneralCtasException;
import com.test.banco.cuenta.model.Cuenta;
import com.test.banco.cuenta.model.Movimiento;
import com.test.banco.cuenta.repository.CuentaRepository;
import com.test.banco.cuenta.repository.MovimientoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kgalarza
 */
@Service
public class MovimientoService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogTransaccionalService logTransaccionalService;

    public List<MovimientoOutDto> findAllMovimientos() {
        List<Movimiento> listMovimientos = movimientoRepository.findAll();
        if (listMovimientos.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros para mostrar");
        }

        List<Movimiento> listaMovimientos = movimientoRepository.findAll();
        return listaMovimientos.stream()
                .map(movimiento -> modelMapper.map(movimiento, MovimientoOutDto.class))
                .collect(Collectors.toList());
    }

    public MovimientoOutDto findById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));
        return modelMapper.map(movimiento, MovimientoOutDto.class);
    }

    public MovimientoOutDto generarMovimineto(MovimientoInDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDto.getIdCuenta()).orElseThrow(() -> new ResourceNotFoundException("No se encontró la cuenta con id: " + movimientoDto.getIdCuenta()));
        Movimiento movimiento = modelMapper.map(movimientoDto, Movimiento.class);
        double valorFinalMovimiento = cuenta.getSaldoEnLinea() + movimiento.getValorMovimiento();

        if (valorFinalMovimiento < 0) {
            throw new ValidacionGeneralCtasException("Saldo no disponible");
        }

        movimiento.setFechaMovimiento(LocalDateTime.now());
        movimiento.setSaldoInicial(cuenta.getSaldoEnLinea());
        movimiento.setSaldoDisponible(valorFinalMovimiento);
        String descripcionMov = movimiento.getValorMovimiento() < 0 ? "Retiro de " + (movimiento.getValorMovimiento() * -1) : "Depósito de " + movimiento.getValorMovimiento();
        movimiento.setDescripcionMovimiento(descripcionMov);
        movimiento = movimientoRepository.save(movimiento);

        cuenta.setSaldoEnLinea(valorFinalMovimiento);
        cuentaRepository.save(cuenta);
        logTransaccionalService.logTransaction("generación movimiento", movimientoDto.getUsuarioSesion(), movimientoDto.getUuid(), "Trama: " + movimientoDto.toString());

        return modelMapper.map(movimiento, MovimientoOutDto.class);
    }

    public MovimientoOutDto updateMovimiento(MovimientoInDto movimientoDto) {
        movimientoRepository.findById(movimientoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrada con id: " + movimientoDto.getId()));

        Movimiento movimientoActualizar = modelMapper.map(movimientoDto, Movimiento.class);
        Movimiento movimientoResp = movimientoRepository.save(movimientoActualizar);
        logTransaccionalService.logTransaction("actualización cuenta", movimientoDto.getUsuarioSesion(), movimientoDto.getUuid(), "Trama: " + movimientoDto.toString());
        return modelMapper.map(movimientoResp, MovimientoOutDto.class);
    }

    public MovimientoOutDto updateParcialMovimiento(MovimientoInDto movimientoDto) {
        movimientoRepository.findById(movimientoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrada con id: " + movimientoDto.getId()));

        Movimiento movimientoActualizar = modelMapper.map(movimientoDto, Movimiento.class);
        Movimiento movimientoResp = movimientoRepository.save(movimientoActualizar);
        logTransaccionalService.logTransaction("actualización movimiento", movimientoDto.getUsuarioSesion(), movimientoDto.getUuid(), "Trama: " + movimientoDto.toString());
        return modelMapper.map(movimientoResp, MovimientoOutDto.class);
    }

    public void deleteMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrada con id: " + id));
        movimientoRepository.deleteById(id);
    }

}
