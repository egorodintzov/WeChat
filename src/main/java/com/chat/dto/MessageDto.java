package com.chat.dto;

import javax.validation.constraints.NotNull;

public class MessageDto {

    @NotNull
    private String message;

    public MessageDto() {}

    public MessageDto(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
