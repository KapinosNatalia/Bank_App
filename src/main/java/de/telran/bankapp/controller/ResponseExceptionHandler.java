package de.telran.bankapp.controller;

import de.telran.bankapp.dto.ErrorDto;
import de.telran.bankapp.exceptions.ManagerCreationException;
import de.telran.bankapp.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException exception) {
        ErrorDto errorDto = new ErrorDto(
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ManagerCreationException.class)
    public ResponseEntity<ErrorDto> handleManagerCreationException(ManagerCreationException exception) {
        return new ResponseEntity<>(
                new ErrorDto(
                        exception.getMessage(),
                        Arrays.toString(exception.getStackTrace())
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleManagerNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
                new ErrorDto(
                        exception.getMessage(),
                        Arrays.toString(exception.getStackTrace())
                ), HttpStatus.NO_CONTENT);
    }

}
