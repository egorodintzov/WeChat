package com.chat.controller.authorization;

import com.chat.dto.AuthDto;
import com.chat.dto.RegDto;
import com.chat.dto.TokenDto;
import com.chat.exceptions.UserAlReadyExistsException;
import com.chat.exceptions.WrongLoginOrPasswordException;
import com.chat.security.jwt.JwtProvider;
import com.chat.service.auth.AuthService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

    @Autowired
    private JwtProvider provider;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (!authService.isCorrectPassword(authDto,userService.findByLogin(authDto.getLogin()))) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody RegDto regDto) {

        if (authService.isUserExists(regDto.getLogin())) {
            throw new UserAlReadyExistsException("User already exists");
        }

        userService.create(regDto);
        return new TokenDto(provider.generateToken(regDto.getLogin()));
    }


    @GetMapping("/auth/facebook")
    public TokenDto authenticateWithFacebook(Principal principal) {
        Map<String,Object> details = (Map<String,Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String login = (String) details.get("email");
        String nickname = (String) details.get("name");
        if(!authService.isUserExists(login)) {
            userService.create(new RegDto(nickname,login,null));
        }
        return new TokenDto(provider.generateToken(login));
    }

}
