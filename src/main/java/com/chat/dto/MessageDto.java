package com.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDto {

    @NotNull
    @Size(min=1)
    private String message;
    @Size(min=1)
    private long idChat;

    public MessageDto() {}

    public MessageDto(String message,long idChat) {
        this.message=message;
        this.idChat=idChat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getIdChat() {
        return idChat;
    }

    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }
}
