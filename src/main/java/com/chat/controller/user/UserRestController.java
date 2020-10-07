package com.chat.controller.user;

import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.photo.PhotoService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


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


    @GetMapping("/search")
    public List<UserDto> findAllByNickname(@RequestParam String nickname) {
        return service.findAllByNickNameStartsWith(nickname);
    }

    @GetMapping("/auth")
    public UserDto getNicknameCurrentUser() {
        return service.getNicknameCurrentUser();
    }

    @PutMapping
    public UpdateUserDto update(@Valid @RequestBody UpdateUserDto updateUserDto) {
        final String LOGIN_CURRENT_USER = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.updateNicknameAndPassword(updateUserDto,LOGIN_CURRENT_USER);
    }

    @PostMapping("/photo")
    public void createPhoto(@RequestBody MultipartFile file) throws IOException {
        final User CURRENT_USER = service.getCurrentUser();
        photoService.createPhoto(file, CURRENT_USER);
    }

    @GetMapping("/photo")
    public PhotoDto getPhoto() {
        return service.getPhoto();
    }

}
