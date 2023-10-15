package de.telran.bankapp.entity.enums;

public enum AccountType {
    CURRENT("CURRENT"),
    CREDIT("CREDIT"),
    DEPOSIT("DEPOSIT");
    private final String value;

    AccountType(String value) {
        this.value = value;
    }
}
