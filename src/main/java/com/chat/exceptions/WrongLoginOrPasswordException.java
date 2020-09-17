package com.chat.exceptions;

public class WrongLoginOrPasswordException extends RuntimeException {

    public WrongLoginOrPasswordException(String message) {
        super(message);
    }
}
