package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ClientWithAccountDto;
import de.telran.bankapp.entity.Account;
import de.telran.bankapp.mapper.ClientMapper;
import de.telran.bankapp.repository.AccountRepository;
import de.telran.bankapp.repository.ClientRepository;
import de.telran.bankapp.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AccountRepository accountRepository;

    @Override
    public List<ClientDto> getClientsWithBalanceMoreThan(BigDecimal amount) {
        return clientMapper.toDtoList(clientRepository.getClientsWithBalanceMoreOrEqualThan(amount));
    }

    @Override
    public List<ClientWithAccountDto> getClientsAndAccountsWithBalanceMoreThan(BigDecimal amount) {
        List<Account> accountsWithBalanceMoreThen = accountRepository.getAccountByBalanceIsGreaterThanEqual(amount);
        return accountsWithBalanceMoreThen.stream()
                .map(clientMapper::clientWithAccountToDto)
                .toList();
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientMapper.toDtoList(clientRepository.findAll());
    }
}
