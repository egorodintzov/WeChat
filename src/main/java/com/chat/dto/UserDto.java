package com.chat.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private String login;

    public UserDto() {}

    public UserDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
