package com.example.bootuserstest.exception;

public class CredentialAreAlreadyInUseException extends RuntimeException {

    public CredentialAreAlreadyInUseException(String message) {
        super(message);
    }
}