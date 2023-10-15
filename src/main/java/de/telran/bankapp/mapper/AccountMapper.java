package de.telran.bankapp.mapper;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
    List<AccountDto> toDtoList(List<Account> accounts);
}
