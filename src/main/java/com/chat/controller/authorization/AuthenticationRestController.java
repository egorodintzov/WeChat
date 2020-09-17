package com.chat.controller.authorization;

import com.chat.dto.AuthDto;
import com.chat.dto.TokenDto;
import com.chat.dto.UserDto;
import com.chat.exceptions.UserAllReadyExistsException;
import com.chat.exceptions.WrongLoginOrPasswordException;
import com.chat.model.Role;
import com.chat.model.User;
import com.chat.security.jwt.JwtProvider;
import com.chat.service.auth.AuthService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationNotSupportedException;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    @Autowired
    private JwtProvider provider;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (authService.isCorrectPassword(authDto)) {
            return new TokenDto(provider.generateToken(authDto.getLogin()));
        }
        throw new WrongLoginOrPasswordException("wrong login or password");
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody AuthDto authDto) {

        if (authService.isCreated(authDto.getLogin())) {
            userService.create(authDto);
            return new TokenDto(provider.generateToken(authDto.getLogin()));
        }
        throw new UserAllReadyExistsException("user all ready exists");
    }


    @PostMapping("/created")
    public boolean isCreated(@Valid @RequestBody UserDto userDto) {
        return authService.isCreated(userDto.getLogin());
    }


    @PostMapping("/correct")
    public boolean isCorrectPassword(@Valid @RequestBody AuthDto authDto) {
        return authService.isCorrectPassword(authDto);
    }

}
