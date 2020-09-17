package com.chat.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MessageContentDto {

    @Min(1)
    private long id;
    @NotNull
    private String message;

    public MessageContentDto() {

    }

    public MessageContentDto(String message) {
        this.message=message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
