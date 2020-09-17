package com.chat.dto;

import javax.validation.constraints.NotNull;

public class TokenDto {

    @NotNull
    private String token;

    public TokenDto() {}

    public TokenDto(String token) {
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
