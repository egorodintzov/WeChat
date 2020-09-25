package com.chat.service.user;

import com.chat.dto.*;
import com.chat.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void create(RegDto regDto);

    void deleteById(long id);

    User findById(long id);

    User findByLogin(String login);

    List<User> getAllUsers();

    PhotoDto getPhoto();

    List<UserDto> findAllByNickNameStartsWith(String login);

    UpdateUserDto updateNicknameAndPassword(UpdateUserDto updateUserDto,String loginCurrentUser);

    void updateChats(User user);

    void updatePhoto(User user);

    User getUserByIndex(Set<User> users, int index);

    UserDto getNicknameCurrentUser();
}
