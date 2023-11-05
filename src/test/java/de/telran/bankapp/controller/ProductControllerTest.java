package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllProducts() throws Exception {
        // when
        MvcResult productsGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, productsGetResult.getResponse().getStatus());
        Set<ProductDto> productDtoSet = objectMapper.readValue(productsGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(4, productDtoSet.size());
    }

    @Test
    void findAllProductsWhereAgreementsQuantityMoreThan() {
    }

    @Test
    void getProductsWithQuantityOfUsing() {
    }
}