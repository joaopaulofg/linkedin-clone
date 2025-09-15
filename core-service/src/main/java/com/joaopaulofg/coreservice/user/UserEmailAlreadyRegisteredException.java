package com.joaopaulofg.coreservice.user;

public class UserEmailAlreadyRegisteredException extends RuntimeException  {
    public UserEmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
