package com.chat.controller.user;

import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.photo.PhotoService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserRestController {

   @Autowired
   private UserService service;

   @Autowired
   private PhotoService photoService;

   @GetMapping("/all")
   @Deprecated
   public List<User> getAll() {
      return service.getAllUsers();
   }


   @GetMapping("/{login}")
   public List<UserDto> findAllByLogin(@PathVariable("login") String login) {
       return service.findAllByLoginStartsWith(login);
   }

   @GetMapping("/auth")
   public UserDto getLoginCurrentUser() {
      return service.getLoginCurrentUser();
   }

   @PutMapping
   public AuthDto update(@RequestBody AuthDto authDto) {
         service.updateLoginAndPassword(authDto);
         User user = service.findByLogin(authDto.getLogin());
         return new AuthDto(user.getLogin(),user.getPassword());
   }

   @PostMapping("/photo")
   public void createPhoto(@RequestBody MultipartFile file) throws IOException {
      photoService.createPhoto(file);
   }

   @GetMapping("/photo")
   public PhotoDto getPhoto() {
      return service.getPhoto();
   }

}
