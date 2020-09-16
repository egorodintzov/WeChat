package com.chat.service.user;

import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.model.User;

import java.util.List;

public interface UserServiceI {

    void create(User user);

    void deleteById(long id);

    User findById(long id);

    User findByLogin(String login);

    List<User> getAllUsers();

    PhotoDto getPhoto();

    List<UserDto> findAllByLoginStartsWith(String login);

    void updateLoginAndPassword(UpdateUserDto user);

    void updateChats(User user);

    void updateMessages(User user);

    void updatePhoto(User user);
}
