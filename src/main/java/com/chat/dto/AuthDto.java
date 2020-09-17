package com.chat.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthDto {

    @NotNull
    @Size(min=2)
    private String login;
    @NotNull
    @Size(min=6)
    private String password;

    public AuthDto() {}

    public AuthDto(String login,String password) {
        this.login=login;
        this.password=password;
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
