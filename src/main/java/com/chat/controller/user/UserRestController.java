package com.chat.controller.user;

import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestController {

   @Qualifier("userServiceImpl")
   @Autowired
   private UserService service;

   @GetMapping("/all")
   @Deprecated
   public List<User> getAll() {
      return service.getAllUsers();
   }


   @GetMapping("/users")
   public List<UserDto> findAllByLogin(@RequestParam String login) {
       return service.findAllByLoginStartsWith(login);
   }

   @GetMapping("/user/auth")
   public UserDto getLoginCurrentUser() {
      return service.getLoginCurrentUser();
   }

   @PutMapping("/user/{login}")
   public AuthDto update(@PathVariable("login") String login, @RequestParam String password) {
         service.updateLoginAndPassword(login,password);
         User user = service.findByLogin(login);
         return new AuthDto(user.getLogin(),user.getPassword());
   }

   @GetMapping("/user/photo")
   public PhotoDto getPhoto() {
      return service.getPhoto();
   }

}
