package de.telran.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorDto {
    //private final String request;
    private final String statusHTTP;
    private final String errorMessage;
}
