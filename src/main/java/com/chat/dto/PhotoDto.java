package com.chat.dto;

public class PhotoDto {

    private byte[] content;

    public PhotoDto() {}

    public PhotoDto(byte[] content) {
        this.content=content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
