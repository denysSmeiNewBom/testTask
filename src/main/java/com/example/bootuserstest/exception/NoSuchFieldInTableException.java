package com.example.bootuserstest.exception;

public class NoSuchFieldInTableException extends RuntimeException{
    public NoSuchFieldInTableException(String message) {
        super(message);
    }
}
