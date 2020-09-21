package com.chat.controller.authorization;

import com.chat.dto.AuthDto;
import com.chat.dto.TokenDto;
import com.chat.dto.UserDto;
import com.chat.exceptions.UserAlReadyExistsException;
import com.chat.exceptions.WrongLoginOrPasswordException;
import com.chat.security.jwt.JwtProvider;
import com.chat.service.auth.AuthService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

    private static Logger log = Logger.getLogger(AuthenticationRestController.class.getName());

    @Autowired
    private JwtProvider provider;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (!authService.isCorrectPassword(authDto)) {
            log.severe("wrong login or password,login-" + authDto.getLogin());
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody AuthDto authDto) {

        if (authService.isCreated(authDto.getLogin())) {
            log.severe("user already exists");
            throw new UserAlReadyExistsException("User already exists");
        }

        userService.create(authDto);
        return new TokenDto(provider.generateToken(authDto.getLogin()));

    }


    @PostMapping("/created")
    public boolean isCreated(@Valid @RequestParam String login) {
        return authService.isCreated(login);
    }


    @PostMapping("/correct")
    public boolean isCorrectPassword(@Valid @RequestBody AuthDto authDto) {
        return authService.isCorrectPassword(authDto);
    }

}
