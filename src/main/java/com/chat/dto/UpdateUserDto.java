package com.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDto {

    @NotNull
    @Size(min=2)
    private String nickname;
    @NotNull
    @Size(min=6)
    private String password;

    public UpdateUserDto() {

    }

    public UpdateUserDto(String nickname,String password) {
        this.nickname=nickname;
        this.password=password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
