package com.chat.dto;

public class UpdateUserDto {

    private String login;
    private String password;
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
