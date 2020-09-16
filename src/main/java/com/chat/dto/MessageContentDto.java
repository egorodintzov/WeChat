package com.chat.dto;

public class MessageContentDto {

    private long id;
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
