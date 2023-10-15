package de.telran.bankapp.service.interfaces;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();
    List<ClientDto> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(LocalDateTime date);
}
