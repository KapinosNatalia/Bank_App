package de.telran.bankapp.dto;

import lombok.*;

@Value
public class ManagerDto {
    String id;
    String firstName;
    String lastName;
    String status;
    String createdAt;
    String updatedAt;
}
