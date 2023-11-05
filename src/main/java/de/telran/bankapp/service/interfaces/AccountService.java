package de.telran.bankapp.service.interfaces;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.entity.enums.AccountStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDto> getAccountsByStatus(AccountStatus status);

    List<ClientDto> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(LocalDateTime date);
}
