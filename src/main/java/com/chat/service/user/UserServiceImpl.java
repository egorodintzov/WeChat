package com.chat.service.user;

import com.chat.dao.UserDao;
import com.chat.dto.*;
import com.chat.exceptions.NoPhotoException;
import com.chat.exceptions.UserNotFoundException;
import com.chat.model.Photo;
import com.chat.model.Role;
import com.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * create user
     * @param regDto - object which contains nickname,login,password
     */

    @Override
    public void create(RegDto regDto) {
        if(regDto.getPassword()!=null) {
            dao.save(new User(regDto.getNickname(),regDto.getLogin(), encoder.encode(regDto.getPassword()), Role.USER));
        }
        else {
            dao.save(new User(regDto.getNickname(),regDto.getLogin(),regDto.getPassword(),Role.USER));
        }
    }

    /**
     * delete user by id
     *
     * @param id
     */

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    /**
     * find user by id
     *
     * @param id
     * @return User object
     */

    @Override
    public User findById(long id) {
        return dao.findById(id).orElseThrow(
                () ->
                    new UserNotFoundException("User not found")
                );
    }

    /**
     * find user by login
     *
     * @param login
     * @return user object
     */

    @Override
    public User findByLogin(String login) {
        User user = dao.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return dao.findByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    /**
     * get photo of current user
     *
     * @return photo dto object
     */

    @Override
    public PhotoDto getPhoto() {
        Photo photo = dao.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getPhoto();
        if (photo == null) {
            throw new NoPhotoException("No photo");
        }

        return new PhotoDto(photo.getContent());
    }

    /**
     * find all users which have login which starts with this words
     *
     * @param nickname
     * @return list with user dto objects
     */

    @Override
    public List<UserDto> findAllByNickNameStartsWith(String nickname) {
        List<UserDto> list = new LinkedList<>();
        List<User> users = dao.findAllByNicknameStartingWith(nickname);
        if (users == null) {
            throw new UserNotFoundException("User not found");
        }

        for (User user : dao.findAllByNicknameStartingWith(nickname)) {
            list.add(new UserDto(user.getNickname()));
        }
        return list;
    }

    /**
     * update nickname and password
     * @param updateUserDto object , which contains new nickname or password
     * @param loginCurrentUser
     * @return updateuserdto object
     */

    @Override
    public UpdateUserDto updateNicknameAndPassword(UpdateUserDto updateUserDto,String loginCurrentUser) {
        User dbUser = findByLogin(loginCurrentUser);
        dbUser.setNickname(updateUserDto.getNickname());
        dbUser.setPassword(updateUserDto.getPassword());
        dao.save(dbUser);
        return new UpdateUserDto(dbUser.getNickname(),dbUser.getPassword());
    }

    /**
     * update photo
     *
     * @param user object
     */

    @Override
    public void updatePhoto(User user) {
        User dbUser = findById(user.getId());
        dbUser.setPhoto(user.getPhoto());
        dao.save(dbUser);
    }

    /**
     * update user chats (use when chat's creating)
     *
     * @param user object
     */

    @Override
    public void updateChats(User user) {
        User dbUser = findById(user.getId());
        dbUser.setChats(user.getChats());
        dao.save(dbUser);
    }


    /**
     * get user from set by index
     *
     * @param users - set collection
     * @param index
     * @return user object
     */

    @Override
    public User getUserByIndex(Set<User> users, int index) {
        int i = 0;
        for (User user : users) {
            if (i == index) {
                return user;
            }
            i++;
        }
        throw new UserNotFoundException("User not found");
    }

    /**
     * get current user
     *
     * @return user dto object
     */

    @Override
    public UserDto getNicknameCurrentUser() {
        final User CURRENT_USER = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return new UserDto(CURRENT_USER.getNickname());
    }

    @Override
    public User getCurrentUser() {
        return findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
