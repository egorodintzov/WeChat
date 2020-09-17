package com.chat.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateUserDto {

    @NotNull
    private String login;
    @NotNull
    private String password;
    @Min(1)
    private long id;

    public UpdateUserDto() {}

    public UpdateUserDto(String login,String password) {
        this.login=login;
        this.password=password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
