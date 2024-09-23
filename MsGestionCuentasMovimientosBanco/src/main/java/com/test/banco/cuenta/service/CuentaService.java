package com.test.banco.cuenta.service;

import com.test.banco.cuenta.dto.CuentaInDto;
import com.test.banco.cuenta.dto.CuentaNoValidInDto;
import com.test.banco.cuenta.dto.CuentaOutDto;
import com.test.banco.cuenta.exception.RegistroDuplicadoException;
import com.test.banco.cuenta.exception.ResourceNotFoundException;
import com.test.banco.cuenta.model.Cuenta;
import com.test.banco.cuenta.repository.CuentaRepository;
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
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogTransaccionalService logTransaccionalService;

    public List<CuentaOutDto> findAllCuentas() {

        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros para mostrar");
        }

        List<Cuenta> listCuentaDto = cuentaRepository.findAll();
        return listCuentaDto.stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaOutDto.class))
                .collect(Collectors.toList());
    }

    public CuentaOutDto findById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
        return modelMapper.map(cuenta, CuentaOutDto.class);
    }

    public CuentaOutDto createCuenta(CuentaInDto cuentaDto) {
        //llamada asincrona a clientes
//        clienteService.getClienteById(cuentaDto.getClienteid());

        if (cuentaRepository.existsByNumeroCuenta(cuentaDto.getNumeroCuenta())) {
            throw new RegistroDuplicadoException("La cuenta ya se encuentra registrada.");
        }
        Cuenta cuenta = modelMapper.map(cuentaDto, Cuenta.class);
        cuenta = cuentaRepository.save(cuenta);
        logTransaccionalService.logTransaction("creación cuenta", cuentaDto.getUsuarioSesion(), cuentaDto.getUuid(), "Trama: " + cuentaDto.toString());
        return modelMapper.map(cuenta, CuentaOutDto.class);
    }

    public CuentaOutDto updateCuenta(CuentaInDto cuentaDto) {
        cuentaRepository.findById(cuentaDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + cuentaDto.getId()));

        Cuenta cuentaActualizar = modelMapper.map(cuentaDto, Cuenta.class);
        Cuenta cuentaResp = cuentaRepository.save(cuentaActualizar);
        logTransaccionalService.logTransaction("actualización cuenta", cuentaDto.getUsuarioSesion(), cuentaDto.getUuid(), "Trama: " + cuentaDto.toString());
        return modelMapper.map(cuentaResp, CuentaOutDto.class);
    }

    public CuentaOutDto updateParcialCuenta(CuentaNoValidInDto cuentaDto) {
        cuentaRepository.findById(cuentaDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + cuentaDto.getId()));

        Cuenta cuentaActualizar = modelMapper.map(cuentaDto, Cuenta.class);
        System.out.println("Datos a actualizar: " + cuentaActualizar.toString());
        Cuenta cuentaResp = cuentaRepository.save(cuentaActualizar);
        logTransaccionalService.logTransaction("actualización cuenta", cuentaDto.getUsuarioSesion(), cuentaDto.getUuid(), "Trama: " + cuentaDto.toString());
        return modelMapper.map(cuentaResp, CuentaOutDto.class);
    }

    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
        cuentaRepository.deleteById(id);
    }

}
