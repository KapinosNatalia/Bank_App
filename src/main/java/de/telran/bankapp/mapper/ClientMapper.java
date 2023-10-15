package de.telran.bankapp.mapper;

import de.telran.bankapp.dto.ClientDto;
import de.telran.bankapp.dto.ClientWithAccountDto;
import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    List<ClientDto> toDtoList(List<Client> clients);

    @Mapping(source = "account.client.id", target = "clientId")
    @Mapping(source = "account.client.firstName", target = "firstName")
    @Mapping(source = "account.client.lastName", target = "lastName")
    @Mapping(source = "account.client.email", target = "email")
    @Mapping(source = "account.client.address", target = "address")
    @Mapping(source = "account.client.phone", target = "phone")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.name", target = "accountNumber")
    @Mapping(source = "account.balance", target = "balance")
    ClientWithAccountDto clientWithAccountToDto(Account account);
}
