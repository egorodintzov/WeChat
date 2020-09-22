package com.chat.service.user;

import com.chat.dao.UserDao;
import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UserDto;
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
     * create chat
     *
     * @param authDto object which contains login and password
     */

    @Override
    public void create(AuthDto authDto) {
        dao.save(new User(authDto.getLogin(), encoder.encode(authDto.getPassword()),Role.USER));
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
                () -> {
                    throw new UserNotFoundException("User not found");
                });
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
     * @param login
     * @return list with user dto objects
     */

    @Override
    public List<UserDto> findAllByLoginStartsWith(String login) {
        List<UserDto> list = new LinkedList<>();
        List<User> users = dao.findAllByLoginStartingWith(login);
        if (users == null) {
            throw new UserNotFoundException("User not found");
        }

        for (User user : dao.findAllByLoginStartingWith(login)) {
            list.add(new UserDto(user.getLogin()));
        }
        return list;
    }

    /**
     * update login and password
     * @param login
     * @param password
     */

    @Override
    public void updateLoginAndPassword(String login,String password) {
        User dbUser = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        dbUser.setLogin(login);
        dbUser.setPassword(password);
        dao.save(dbUser);
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
    public UserDto getLoginCurrentUser() {
        return new UserDto(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
