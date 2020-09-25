package com.chat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegDto {

    @NotNull
    @Size(min=2)
    private String nickname;
    @NotNull
    @Size(min=6)
    private String login;
    @NotNull
    @Size(min=6)
    private String password;

    public RegDto() {}

    public RegDto(String nickname,String login,String password) {
        this.nickname=nickname;
        this.login=login;
        this.password=password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
