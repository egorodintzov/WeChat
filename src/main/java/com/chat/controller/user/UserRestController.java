package com.chat.controller.user;

import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.exceptions.UserNotFoundException;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/u")
public class UserRestController {

   @Qualifier("userServiceImpl")
   @Autowired
   private UserService service;

   @GetMapping("/users")
   public List<UserDto> findAllByLogin(@RequestBody UserDto userDto) {
       return service.findAllByLoginStartsWith(userDto.getLogin());
   }

   @GetMapping("/user")
   public User findByLogin(@RequestBody UserDto dto) {
      if(dto!=null)
         return service.findByLogin(dto.getLogin());
      throw new UserNotFoundException("user not found");
   }

   // method for test (will delete)

   @GetMapping("/all")
   public List<User> getAll() {
      return service.getAllUsers();
   }

   @GetMapping("/auth/user")
   public UserDto getCurrentUser() {
      return new UserDto(SecurityContextHolder.getContext().getAuthentication().getName());
   }

   @PutMapping("/update")
   public void update(@RequestBody UpdateUserDto userDto) {
      if(userDto!=null)
         service.updateLoginAndPassword(userDto);
   }

   @GetMapping("/photo")
   public PhotoDto getPhoto() {
      return service.getPhoto();
   }

}
