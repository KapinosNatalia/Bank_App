package de.telran.bankapp.controller;

import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ClientWithAccountDto;
import de.telran.bankapp.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/with-balance-more-than/{amount}")
    public ResponseEntity<List<ClientDto>> getClientsWithBalanceMoreThan(@PathVariable("amount") BigDecimal amount) {
        return ResponseEntity.ok(clientService.getClientsWithBalanceMoreThan(amount));
    }

    @GetMapping("/clients-accounts-with-balance-more-than/{amount}")
    public ResponseEntity<List<ClientWithAccountDto>> getClientsAndAccountsWithBalanceMoreThan(@PathVariable("amount") BigDecimal amount) {
        return ResponseEntity.ok(clientService.getClientsAndAccountsWithBalanceMoreThan(amount));
    }
}
