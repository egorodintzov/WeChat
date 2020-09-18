package com.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDto {

    @NotNull
    @Size(min=1)
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
