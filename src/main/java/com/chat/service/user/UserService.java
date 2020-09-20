package com.chat.service.user;

import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
import com.chat.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void create(AuthDto authDto);

    void deleteById(long id);

    User findById(long id);

    User findByLogin(String login);

    List<User> getAllUsers();

    PhotoDto getPhoto();

    List<UserDto> findAllByLoginStartsWith(String login);

    void updateLoginAndPassword(UpdateUserDto user);

    void updateChats(User user);

    void updatePhoto(User user);

    User getUserByIndex(Set<User> users, int index);

    UserDto getCurrentUser();
}
