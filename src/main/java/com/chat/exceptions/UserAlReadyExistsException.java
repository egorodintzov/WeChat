package com.chat.exceptions;

public class UserAlReadyExistsException extends RuntimeException {

    public UserAlReadyExistsException(String message) {
        super(message);
    }
}
