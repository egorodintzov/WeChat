package com.chat.service.auth;


import com.chat.dto.AuthDto;

public interface AuthService {

    boolean isCreated(String login);

    boolean isCorrectPassword(AuthDto authDto);

}
