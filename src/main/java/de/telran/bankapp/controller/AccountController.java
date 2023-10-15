package de.telran.bankapp.controller;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/delete-accounts-without-transactions-and-created-earlier-than")
    public ResponseEntity<List<ClientDto>> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(@RequestParam("date")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(accountService.markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(date));
    }
}
