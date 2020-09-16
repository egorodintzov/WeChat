package com.chat.controller.user;

import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserRestController {

   @Qualifier("userServiceImpl")
   @Autowired
   private UserService service;

   @PostMapping("/users")
   public List<UserDto> findAllByLogin(@RequestBody UserDto userDto) {
       return service.findAllByLoginStartsWith(userDto.getLogin());
   }

   @GetMapping("/user")
   public User findByLogin(@RequestBody UserDto dto) {
      return service.findByLogin(dto.getLogin());
   }

   @GetMapping("/all")
   public List<User> getAll() {
      return service.getAllUsers();
   }

   @GetMapping("/auth/me")
   public UserDto getCurrentUser() {
      return new UserDto(SecurityContextHolder.getContext().getAuthentication().getName());
   }

   @PutMapping("/update")
   public void update(@RequestBody UpdateUserDto userDto) {
      service.updateLoginAndPassword(userDto);
   }

   @GetMapping("/photo")
   public PhotoDto getPhoto() {
      return service.getPhoto();
   }

}
