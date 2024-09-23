package com.test.banco.cliente.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.banco.cliente.dto.ClienteInDto;
import com.test.banco.cliente.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 * @author kgalarza
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Rollback(false)
    void testCrearCliente() throws Exception {
        ClienteInDto nuevoCliente = new ClienteInDto();
        nuevoCliente.setNombreCliente("Carlos López");
        nuevoCliente.setGeneroCliente("M");
        nuevoCliente.setEdadCliente(35);
        nuevoCliente.setIdentificacionCliente("0987654321");
        nuevoCliente.setDireccionCliente("Av. Siempre Viva 123");
        nuevoCliente.setTelefonoCliente("0998765432");
        nuevoCliente.setContrasenaCliente("securePass");
        nuevoCliente.setEstadoCliente(true);
        nuevoCliente.setUsuarioSesion("clopez");
        nuevoCliente.setUuid("0987654321-abcdef");

        String nuevoClienteJson = objectMapper.writeValueAsString(nuevoCliente);

        mockMvc.perform(post("/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nuevoClienteJson))
                .andExpect(status().isCreated());
    }

}
