package de.telran.bankapp.controller;

import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.Client;
import de.telran.bankapp.entity.enums.AccountStatus;
import de.telran.bankapp.entity.enums.AccountType;
import de.telran.bankapp.repository.AccountRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountControllerTest {
    @Mock
    Client client;
//
//    @LocalServerPort
//    private Integer port;

//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
//            "postgres:15-alpine"
//    );
//
//    @BeforeAll
//    static void beforeAll() {
//        postgres.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgres.stop();
//    }
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }


    @Autowired
    AccountRepository accountRepository;

//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//        accountRepository.deleteAll();
//    }

    @Test
    void shouldGetAllAccounts() {
//        List<Account> accounts = List.of(
//          new Account("523e4567-e89b-12d3-a456-040000000001", client, "account1", AccountType.CURRENT, AccountStatus.ACTIVE, 1000, "EUR"),
//          new Account("523e4567-e89b-12d3-a456-040000000002", client, "account2", AccountType.CURRENT, AccountStatus.ACTIVE, 2000, "EUR")
//        );
//        accountRepository.saveAll(accounts);
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/accounts")
//                .then()
//                .statusCode(200)
//                .body(".", hasSize(2));
    }

    @Test
    void markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan() {
    }
}