package com.chat.controller.user;

import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/u")
public class UserRestController {

   @Qualifier("userServiceImpl")
   @Autowired
   private UserService service;

   @GetMapping("/users")
   public List<UserDto> findAllByLogin(@Valid @RequestBody UserDto userDto) {
       return service.findAllByLoginStartsWith(userDto.getLogin());
   }

   @GetMapping("/user")
   public User findByLogin(@Valid @RequestBody UserDto dto) {
         return service.findByLogin(dto.getLogin());
   }

   // method for test (will delete)

   @GetMapping("/all")
   public List<User> getAll() {
      return service.getAllUsers();
   }

   @GetMapping("/auth/user")
   public UserDto getCurrentUser() {
      return service.getCurrentUser();
   }

   @PutMapping("/update")
   public void update(@Valid @RequestBody UpdateUserDto userDto) {
         service.updateLoginAndPassword(userDto);
   }

   @GetMapping("/photo")
   public PhotoDto getPhoto() {
      return service.getPhoto();
   }

}
