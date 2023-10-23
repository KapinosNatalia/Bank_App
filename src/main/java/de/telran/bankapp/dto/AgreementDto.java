package de.telran.bankapp.dto;

import lombok.Value;

@Value
public class AgreementDto {
    String creationDate;
    String clientName;
    String account;
    String product;
    String amountFromAgreement;
    String amountFromAccount;
    String status;
}
