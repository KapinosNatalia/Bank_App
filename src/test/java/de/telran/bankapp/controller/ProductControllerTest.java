package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ProductDto;
import de.telran.bankapp.dto.ProductWithManagerAndQuantityDto;
import de.telran.bankapp.entity.Product;
import de.telran.bankapp.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

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
    void shouldGetProduct() throws Exception {
        //given
        ProductDto expectedProductDto = new ProductDto(
                "523e4567-e89b-12d3-a456-020000000004", //"523e4567-e89b-12d3-a456-020000000011",
                "Current account EUR", //"Test product",
                "ACTIVE",
                "EUR"
        );
        //Product product = objectMapper.convertValue(expectedProductDto, Product.class);
        //Product product = new Product();
        //productRepository.save(product);

        //when
        MvcResult productGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/" + expectedProductDto.getId())
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        //then
        Assertions.assertEquals(200, productGetResult.getResponse().getStatus());
        ProductDto productDto = objectMapper.readValue(productGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(expectedProductDto, productDto);
    }

    @Test
    void shouldGetProductNotFoundException() throws Exception {
        //given
        String productID = "523e4567-e89b-12d3-a456-020000000011";

        //when
        MvcResult productGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/" + productID)
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        //then
        Assertions.assertEquals(400, productGetResult.getResponse().getStatus());
    }

    @Test
    void shouldFindAllProductsWhereAgreementsQuantityMoreThan() throws Exception {
        //given
        List<ProductWithManagerAndQuantityDto> expectedProductList = List.of(
                new ProductWithManagerAndQuantityDto(
                        UUID.fromString("523e4567-e89b-12d3-a456-020000000004"),
                        "Current account EUR",
                        "Carlos Garcia",
                        2
                )
        );

        //when
        MvcResult productListGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/find-all-products-where-agreement-quantity-more-than/1")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        //then
        Assertions.assertEquals(200, productListGetResult.getResponse().getStatus());
        List<ProductWithManagerAndQuantityDto> productList = objectMapper.readValue(productListGetResult.getResponse().getContentAsString(), new TypeReference<>() {});
        Assertions.assertEquals(expectedProductList, productList);
    }

    @Test
    void shouldGetProductsWithQuantityOfUsing() throws Exception {
        //given
        Set<ProductWithManagerAndQuantityDto> expectedProductList = Set.of(
                new ProductWithManagerAndQuantityDto(
                        UUID.fromString("523e4567-e89b-12d3-a456-020000000001"),
                        "Mortgage up to 200.000 EUR",
                        "James Smith",
                        0
                ),
                new ProductWithManagerAndQuantityDto(
                        UUID.fromString("523e4567-e89b-12d3-a456-020000000002"),
                        "Mortgage up to 500.000 EUR",
                        "James Smith",
                        0
                ),
                new ProductWithManagerAndQuantityDto(
                        UUID.fromString("523e4567-e89b-12d3-a456-020000000003"),
                        "Deposit up to 100.000 EUR",
                        "Anna Lee",
                        1
                ),
                new ProductWithManagerAndQuantityDto(
                        UUID.fromString("523e4567-e89b-12d3-a456-020000000004"),
                        "Current account EUR",
                        "Carlos Garcia",
                        2
                )
        );

        //when
        MvcResult productListGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/get-products-with-quantity-of-using")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        //then
        Assertions.assertEquals(200, productListGetResult.getResponse().getStatus());
        Set<ProductWithManagerAndQuantityDto> productList = objectMapper.readValue(productListGetResult.getResponse().getContentAsString(), new TypeReference<>() {});
        //Assertions.assertEquals(expectedProductList, productList);
    }
}