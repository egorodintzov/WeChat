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
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationNotSupportedException;
import java.util.logging.Logger;

@RestController
public class AuthenticationRestController {

     static Logger logger = Logger.getLogger(AuthenticationRestController.class.getName());

     @Autowired
     private JwtProvider provider;

     @Autowired
     private AuthService authService;

     @Autowired
     private UserService userService;

     @PostMapping("/authenticate")
     public TokenDto authenticate(@RequestBody UserDto userDto) {
         if(userDto!=null) {
              logger.info("true");
              return new TokenDto(provider.generateToken(userDto.getLogin()));
         }
         return null;
     }

     @PostMapping("/registration")
     public TokenDto registration(@RequestBody AuthDto authDto) {
          if(authDto!=null) {
               User user = new User(authDto.getLogin(),authDto.getPassword());
               user.setRole(Role.USER);
               userService.create(user);
               return new TokenDto(provider.generateToken(authDto.getLogin()));
          }
          return null;
     }

     @PostMapping("/checkCreated")
     public boolean checkIsCreated(@RequestBody UserDto authDto) {
          return authService.checkIsCreated(authDto);
     }


}
