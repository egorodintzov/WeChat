package com.chat.service.auth;


import com.chat.dto.AuthDto;
import com.chat.model.User;

public interface AuthService {

    boolean isUserExists(String login);

    boolean isCorrectPassword(AuthDto regDto, User user);

}
