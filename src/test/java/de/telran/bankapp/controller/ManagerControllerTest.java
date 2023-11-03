package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.exceptions.ManagerCreationException;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    void shouldGetAllManagers() throws Exception {
        // when
        MvcResult managerGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/managers")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());
        Set<ManagerDto> managerDtoSet = objectMapper.readValue(managerGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(4, managerDtoSet.size());
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

        MvcResult managerDeleteResult = mockMvc
                .perform(MockMvcRequestBuilders.delete("/managers/" + managerDto.getId())
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(201, managerCreationResult.getResponse().getStatus());
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());
        Assertions.assertEquals(204, managerDeleteResult.getResponse().getStatus());

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

        MvcResult managerGetResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/managers/" + managerDto.getId())
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(400, managerCreationResult.getResponse().getStatus());
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());
    }

    @Test
    void shouldUpdateManager() throws Exception {
        // given
        ManagerDto managerDto = new ManagerDto("523e4567-e89b-12d3-a456-010000000001",
                "NewFirstName",
                "NewLastName",
                "WORKING");
        String managerStr = objectMapper.writeValueAsString(managerDto);

        // when
        MvcResult managerUpdateResult = mockMvc.perform(MockMvcRequestBuilders.put("/managers")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.with(httpBasic("user", "password"))
                        //.with(csrf())
                        .content(managerStr))
                .andReturn();
        MvcResult managerGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/managers/" + managerDto.getId())
                //.with(httpBasic("user", "password"))
                //.with(csrf())
                ).andReturn();

        // then
        Assertions.assertEquals(200, managerUpdateResult.getResponse().getStatus());
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());
        String receivedManagerJson = managerGetResult.getResponse().getContentAsString();
        ManagerDto receivedManagerDto = objectMapper.readValue(receivedManagerJson, ManagerDto.class);
        Assertions.assertEquals(managerDto.getLastName(), receivedManagerDto.getLastName());
        Assertions.assertEquals(managerDto.getFirstName(), receivedManagerDto.getFirstName());
    }

    @Test
    void shouldDeleteManagerByID() throws Exception {
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
        MvcResult managerDeletionResult = mockMvc.perform(MockMvcRequestBuilders.delete("/managers/" + managerDto.getId())
                        //.with(httpBasic("user", "password"))
                        //.with(csrf())
                        )
                .andReturn();
        MvcResult managerGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/managers/" + managerDto.getId())
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(201, managerCreationResult.getResponse().getStatus());
        Assertions.assertEquals(204, managerDeletionResult.getResponse().getStatus());
        Assertions.assertEquals(400, managerGetResult.getResponse().getStatus());
    }

    @Test
    void shouldGetAllManagersByClientStatus() throws Exception {
        // given
        String status = "VIP";
        Set<ManagerDto> expectedManagers;
        ManagerDto managerDto1 = new ManagerDto(
                "523e4567-e89b-12d3-a456-010000000001",
                "James",
                "Smith",
                "WORKING"
        );
        ManagerDto managerDto2 = new ManagerDto(
                "523e4567-e89b-12d3-a456-010000000003",
                "Anna",
                "Lee",
                "FIRED"
        );
        expectedManagers = Set.of(managerDto1, managerDto2);

        // when
        MvcResult managerGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/managers/get-all-by-clients-status/" + status)
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, managerGetResult.getResponse().getStatus());
        Set<ManagerDto> managerDtoSet = objectMapper.readValue(managerGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(expectedManagers, managerDtoSet);
    }
}