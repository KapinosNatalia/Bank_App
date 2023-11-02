package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.mapper.ManagerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Sql()
class ManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllManagers() {
    }

    @Test
    void getManagerByID() {
    }

    @Test
    void shouldCreateManager() throws Exception {
        // given
        ManagerDto managerDto = new ManagerDto("523e4567-e89b-12d3-a456-010000000011",
                "John",
                "Smith",
                "WORKING");

        String managerStr = objectMapper.writeValueAsString(managerDto);

        // when
        MvcResult managerCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/managers")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.with(httpBasic("user", "password"))
                        //.with(csrf())
                        .content(managerStr))
                .andReturn();

        MvcResult managerGetResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/managers/" + managerDto.getId())
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(201, managerCreationResult.getResponse().getStatus());
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());

        String managerGetStringDto = managerGetResult.getResponse().getContentAsString();
        ManagerDto receivedManagerDto = objectMapper.readValue(managerGetStringDto, ManagerDto.class);

        Assertions.assertEquals(managerDto, receivedManagerDto);
    }

    @Test
    void shouldReceiveCreationException() throws Exception {
        // given
        ManagerDto managerDto = new ManagerDto("523e4567-e89b-12d3-a456-010000000001",
                "John",
                "Smith",
                "WORKING");

        String managerStr = objectMapper.writeValueAsString(managerDto);

        // when
        MvcResult managerCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/managers")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.with(httpBasic("user", "password"))
                        //.with(csrf())
                        .content(managerStr))
                .andReturn();

//        MvcResult managerGetResult = mockMvc
//                .perform(MockMvcRequestBuilders.get("/managers/" + managerDto.getId())
//                        //.with(httpBasic("user", "password"))
//                )
//                .andReturn();

        // then
        Assertions.assertEquals(400, managerCreationResult.getResponse().getStatus());
        Assertions.assertEquals("Manager with ID " + managerDto.getId() + " already exists", managerCreationResult.getResponse().getContentAsString());
//        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());

//        String managerGetStringDto = managerGetResult.getResponse().getContentAsString();
//        ManagerDto receivedManagerDto = objectMapper.readValue(managerGetStringDto, ManagerDto.class);

//        Assertions.assertEquals(managerDto, receivedManagerDto);
    }

    @Test
    void updateManager() {
    }

    @Test
    void deleteManagerByID() {
    }

    @Test
    void getAllManagersByClientStatus() {
    }
}