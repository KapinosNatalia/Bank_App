package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllClients() throws Exception {
        // when
        MvcResult clientsGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/clients")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, clientsGetResult.getResponse().getStatus());
        Set<ClientDto> clientDtoSet = objectMapper.readValue(clientsGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(5, clientDtoSet.size());
    }

    @Test
    void getClientsWithBalanceMoreThan() {
    }

    @Test
    void getClientsAndAccountsWithBalanceMoreThan() {
    }
}