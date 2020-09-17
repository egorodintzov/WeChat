package com.chat.exceptions;

public class UserAllReadyExistsException extends RuntimeException {

    public UserAllReadyExistsException(String message) {
        super(message);
    }
}
