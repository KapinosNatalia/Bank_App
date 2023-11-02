package de.telran.bankapp.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorDto {
    String errorMessage;
    String errorStackTrace;
}
