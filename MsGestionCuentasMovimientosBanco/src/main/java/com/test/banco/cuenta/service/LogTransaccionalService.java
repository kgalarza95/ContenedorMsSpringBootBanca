package com.test.banco.cuenta.service;

import com.test.banco.cuenta.model.LogTransaccional;
import com.test.banco.cuenta.repository.LogTransaccionalRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kgalarza
 */
@Service
public class LogTransaccionalService {

    @Autowired
    private LogTransaccionalRepository logTransaccionalRepository;

    public void logTransaction(String accion, String usuario, String uuid, String trama) {
        LogTransaccional log = new LogTransaccional();
        log.setAccion(accion);
        log.setUsuario(usuario);
        log.setUuid(uuid);
        log.setTrama(trama);
        log.setFecha(LocalDateTime.now());
        logTransaccionalRepository.save(log);
    }
}
