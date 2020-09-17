package com.chat.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ChatDto {

    @NotNull
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
