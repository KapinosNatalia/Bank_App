package de.telran.bankapp.entity.enums;

public enum ProductStatus {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    OUTDATED("OUTDATED");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }
}
