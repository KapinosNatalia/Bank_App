package de.telran.bankapp.controller;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.service.interfaces.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Account controller API", description = "With this controller you can get a list of all accounts or delete " +
        "accounts for which there were no transactions and those created before a specific date. As a response," +
        " you will receive a list of clients (owners of these accounts) with their contact information")
public class AccountController {
    private final AccountService accountService;

    @GetMapping()
    @Operation(summary = "Get list of all accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/delete-accounts-without-transactions-and-created-earlier-than")
    @Operation(summary = "Delete (change status) accounts for which there were no transactions and those created before a specific date",
            description = "As a response, you will receive a list of clients (owners of these accounts) with their contact information." +
                    " The 'date' parameter is passed as a query params")
    public ResponseEntity<List<ClientDto>> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(@RequestParam("date")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(accountService.markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(date));
    }
}