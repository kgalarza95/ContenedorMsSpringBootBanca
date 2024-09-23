package com.test.banco.cliente.service;

import com.test.banco.cliente.dto.ClienteInDto;
import com.test.banco.cliente.dto.ClienteInOutDto;
import com.test.banco.cliente.exception.ResourceNotFoundException;
import com.test.banco.cliente.model.Cliente;
import com.test.banco.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogTransaccionalService logTransaccionalService;

    public List<ClienteInOutDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros para mostrar");
        }
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteInOutDto.class))
                .collect(Collectors.toList());
    }

    public ClienteInOutDto getClienteById(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteId));
        return modelMapper.map(cliente, ClienteInOutDto.class);
    }

    public ClienteInOutDto createCliente(ClienteInDto clienteDto) {
        System.out.println("cliente services  " + clienteDto.toString());
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        System.out.println("cliente in: " + cliente);
        cliente = clienteRepository.save(cliente);
        System.out.println("cliente out: " + cliente);

        logTransaccionalService.logTransaction("creación usuario", clienteDto.getUsuarioSesion(), clienteDto.getUuid(), "Trama: " + clienteDto.toString());

        return modelMapper.map(cliente, ClienteInOutDto.class);
    }

    public ClienteInOutDto updateCliente(Long clienteid, ClienteInDto clienteDto) {
        Cliente existingCliente = clienteRepository.findByClienteid(clienteid);
        if (existingCliente != null) {
            Cliente clienteActualizar = modelMapper.map(clienteDto, Cliente.class);
            Cliente clienteResp = clienteRepository.save(clienteActualizar);
            return modelMapper.map(clienteResp, ClienteInOutDto.class);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional
    public ClienteInOutDto updateCliente(ClienteInDto clienteDto) {
        clienteRepository.findById(clienteDto.getClienteidCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteDto.getClienteidCliente()));

        Cliente clienteActualizar = modelMapper.map(clienteDto, Cliente.class);
        Cliente clienteResp = clienteRepository.save(clienteActualizar);

        return modelMapper.map(clienteResp, ClienteInOutDto.class);
    }

    public void deleteCliente(Long clienteid) {
        clienteRepository.findById(clienteid)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteid));

        clienteRepository.deleteById(clienteid);
    }

}
