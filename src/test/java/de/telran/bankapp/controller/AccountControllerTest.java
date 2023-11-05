package de.telran.bankapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
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
    void shouldGetAllAccounts() throws Exception {

        // when
        MvcResult accountsGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, accountsGetResult.getResponse().getStatus());
        Set<AccountDto> accountDtoSet = objectMapper.readValue(accountsGetResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(7, accountDtoSet.size());
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
    void markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan() throws Exception {
        // given
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 0, 0);
        MvcResult accountsBeforeDeleteResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/FOR_DELETION")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();
        List<ClientDto> exceptedClientDtoList = getClientDtoList();

        // when
        MvcResult accountsDeleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/delete-accounts-without-transactions-and-created-earlier-than")
                        //.with(httpBasic("user", "password"))
                        .param("date", date.toString())
                )
                .andReturn();
        List<ClientDto> clientDtoList = objectMapper.readValue(accountsDeleteResult.getResponse().getContentAsString(), new TypeReference<>() {});

        MvcResult accountsAfterDeleteResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/FOR_DELETION")
                        //.with(httpBasic("user", "password"))
                )
                .andReturn();

        // then
        Assertions.assertEquals(200, accountsBeforeDeleteResult.getResponse().getStatus());
        Set<AccountDto> accountDtoSet = objectMapper.readValue(accountsBeforeDeleteResult.getResponse().getContentAsString(), new TypeReference<>() {});
        Assertions.assertEquals(0, accountDtoSet.size());

        Assertions.assertEquals(200, accountsDeleteResult.getResponse().getStatus());
        Assertions.assertEquals(exceptedClientDtoList, clientDtoList);
        Assertions.assertEquals(200, accountsAfterDeleteResult.getResponse().getStatus());
        accountDtoSet = objectMapper.readValue(accountsAfterDeleteResult.getResponse().getContentAsString(), new TypeReference<>() {});
        Assertions.assertEquals(2, accountDtoSet.size());
        List<String> uuidList = accountDtoSet.stream()
                        .map(AccountDto::getId)
                                .toList();
        Assertions.assertTrue(uuidList.contains("523e4567-e89b-12d3-a456-040000000003") && uuidList.contains("523e4567-e89b-12d3-a456-040000000006"));


    }

    @NotNull
    private static List<ClientDto> getClientDtoList() {
        ClientDto clientDto1 = new ClientDto(
                "523e4567-e89b-12d3-a456-030000000001",
                "Lukas",
                "Muller",
                "lukas.muller@web.de",
                "Lansstrasse 81, D-11179 Berlin, Germany",
                "+49 30 5684962"
        );
        ClientDto clientDto2 = new ClientDto(
                "523e4567-e89b-12d3-a456-030000000003",
                "Lena",
                "Weber",
                "lena.weber@yahoo.de",
                "Hauptstrasse 25, D-50667 Koln, Germany",
                "+49 221 9876543"
        );
        List<ClientDto> exceptedClientDtoList = List.of(clientDto1, clientDto2);
        return exceptedClientDtoList;
    }
}