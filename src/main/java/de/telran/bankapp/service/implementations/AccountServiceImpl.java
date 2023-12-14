package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.enums.AccountStatus;
import de.telran.bankapp.mapper.AccountMapper;
import de.telran.bankapp.repository.AccountRepository;
import de.telran.bankapp.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    @Override
    public List<AccountDto> getAccountsByStatus(AccountStatus status) {
        List<Account> accountList = accountRepository.getAccountsByStatusIs(status);
        return accountMapper.toDtoList(accountList);
    }

    @Override
    @Transactional
    public List<AccountDto> markForDeletionAccountsWithoutTransactionsAndCreatedEarlierThan(LocalDateTime date) {
        List<Account> accountList = accountRepository.getAccountsWithoutTransactionsAndCreatedEarlierThan(date.toLocalDate().toString(), AccountStatus.FOR_DELETION.name());
        return accountMapper.toDtoList(accountList);
    }
}
