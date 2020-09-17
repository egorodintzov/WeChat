package com.chat.service.auth;


import com.chat.dto.AuthDto;

public interface AuthService {

    boolean checkIsCreated(String login);

    boolean checkUser(AuthDto authDto);

}
