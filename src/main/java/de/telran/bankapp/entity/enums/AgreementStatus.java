package de.telran.bankapp.entity.enums;

public enum AgreementStatus {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    OUTDATED("CLOSED");

    private final String value;

    AgreementStatus(String value) {
        this.value = value;
    }
}
