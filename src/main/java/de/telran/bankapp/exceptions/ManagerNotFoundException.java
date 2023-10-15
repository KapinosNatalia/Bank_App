package de.telran.bankapp.exceptions;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(String message) {
        super("Manager " + message + " is not found.");
    }
}
