package com.chat.dto;

public class UserDto {

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
