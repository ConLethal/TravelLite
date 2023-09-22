package com.travellite2.travellite2.register.service;

public class DuplicateUserNameException extends RuntimeException {

    public DuplicateUserNameException(String message) {
        super(message);
    }
}
