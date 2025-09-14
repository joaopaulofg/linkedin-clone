package com.joaopaulofg.coreservice.domain.exception;

public class EmailAlreadyRegisteredException extends RuntimeException  {
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
