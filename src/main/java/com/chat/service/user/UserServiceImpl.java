package com.chat.service.user;

import com.chat.dao.UserDao;
import com.chat.dto.AuthDto;
import com.chat.dto.PhotoDto;
import com.chat.dto.UpdateUserDto;
import com.chat.dto.UserDto;
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
     * @param authDto object which contains login and password
     */

    @Override
    public void create(AuthDto authDto) {
        User user = new User(authDto.getLogin(),encoder.encode(authDto.getPassword()));
        user.setRole(Role.USER);
        dao.save(user);
    }

    /**
     * delete user by id
     * @param id
     */

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    /**
     * find user by id
     * @param id
     * @return user object
     */

    @Override
    public User findById(long id) {
        return dao.findById(id);
    }

    /**
     * find user by login
     * @param login
     * @return user object
     */

    @Override
    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    /**
     * get photo of current user
     * @return photo dto object
     */

    @Override
    public PhotoDto getPhoto() {
        Photo photo = dao.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getPhoto();
        if(photo!=null)
            return new PhotoDto(photo.getContent());
        return null;
    }

    /**
     * find all users which have login which starts with this words
     * @param login
     * @return list with user dto objects
     */

    @Override
    public List<UserDto> findAllByLoginStartsWith(String login) {
        List<UserDto> list = new LinkedList<>();
        for(User user : dao.findAllByLoginStartingWith(login)) {
            list.add(new UserDto(user.getLogin()));
        }
        return list;
    }

    /**
     * update login and password
     * @param user object
     */

    @Override
    public void updateLoginAndPassword(UpdateUserDto user) {
        User dbUser= findById(user.getId());
        dbUser.setLogin(user.getLogin());
        dbUser.setPassword(user.getPassword());
        dao.save(dbUser);
    }

    /**
     * update photo
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
     * @param user object
     */

    @Override
    public void updateChats(User user) {
        User dbUser = findById(user.getId());
        dbUser.setChats(user.getChats());
        dao.save(dbUser);
    }

    /**
     * update user messages (use when message's creating)
     * @param user object
     */

    @Override
    public void updateMessages(User user) {
        User dbUser = findById(user.getId());
        dbUser.setListMessages(user.getListMessages());
        dao.save(dbUser);
    }

    @Override
    public User getUserByIndex(Set<User> users, int index) {
        int i=0;
        for(User user : users) {
            if(i==index)
               return user;
            i++;
        }
        throw new UserNotFoundException("user not found");
    }

}
