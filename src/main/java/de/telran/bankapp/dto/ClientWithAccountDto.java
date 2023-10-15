package de.telran.bankapp.dto;

import lombok.Value;

@Value
public class ClientWithAccountDto {
    String clientId;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    String accountId;
    String accountNumber;
    String balance;
}
