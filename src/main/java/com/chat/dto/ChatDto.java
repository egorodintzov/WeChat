package com.chat.dto;

import java.util.List;

public class ChatDto {

    private List<UserDto> list;

    public ChatDto() {}

    public ChatDto(List<UserDto> list) {
        this.list = list;
    }

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }
}
