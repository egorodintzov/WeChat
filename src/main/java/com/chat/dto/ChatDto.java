package com.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ChatDto {

    @NotNull
    @Size(min=1,max = 2)
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
