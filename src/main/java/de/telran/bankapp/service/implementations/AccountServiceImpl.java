package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.Client;
import de.telran.bankapp.entity.enums.AccountStatus;
import de.telran.bankapp.mapper.AccountMapper;
import de.telran.bankapp.mapper.ClientMapper;
import de.telran.bankapp.repository.AccountRepository;
import de.telran.bankapp.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientMapper clientMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    @Override
    public List<ClientDto> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(LocalDateTime date) {
        List<Account> accountList = accountRepository.getAccountsWithoutTransactionsAndCreatedEarlierThan(date, AccountStatus.FOR_DELETION);
        List<Client> clientList = new ArrayList<>();
        for (Account account: accountList) {
            account.setStatus(AccountStatus.FOR_DELETION);
            accountRepository.save(account);
            if (!clientList.contains(account.getClient())) {
                clientList.add(account.getClient());
            }
        }
        return clientMapper.toDtoList(clientList);
    }
}
