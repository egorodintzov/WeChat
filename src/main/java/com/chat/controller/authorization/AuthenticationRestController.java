package com.chat.controller.authorization;

import com.chat.dto.AuthDto;
import com.chat.dto.TokenDto;
import com.chat.dto.UserDto;
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
     public TokenDto authenticate(@RequestBody AuthDto authDto) {

         if(authDto!=null && authService.checkIsCreated(authDto.getLogin()) && authService.checkUser(authDto)) {
              return new TokenDto(provider.generateToken(authDto.getLogin()));
         }
         return new TokenDto("no token");
     }

     @PostMapping("/registration")
     public TokenDto registration(@RequestBody AuthDto authDto) {
          if(authDto!=null && authService.checkIsCreated(authDto.getLogin())) {
               userService.create(authDto);
               return new TokenDto(provider.generateToken(authDto.getLogin()));
          }
          return new TokenDto("no token");
     }

     @PostMapping("/created")
     public boolean checkIsCreated(@RequestBody UserDto userDto) {
          if(userDto!=null)
             return authService.checkIsCreated(userDto.getLogin());
          return false;
     }

     @PostMapping("/correct")
     public boolean checkUser(@RequestBody AuthDto authDto) {
          if(authDto!=null)
             return authService.checkUser(authDto);
          return false;
     }
}
