package de.telran.bankapp.service.interfaces;

import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ClientWithAccountDto;
import de.telran.bankapp.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    List<ClientDto> getClientsWithBalanceMoreThan(BigDecimal amount);

    List<ClientWithAccountDto> getClientsAndAccountsWithBalanceMoreThan(BigDecimal amount);

    List<ClientDto> getAllClients();

    Client findClientByEmail(String email);
}
