package com.chat.service.auth;


import com.chat.dao.UserDao;
import com.chat.dto.AuthDto;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @author Egor Odintsov
 */

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDao dao;

    /**
     * check is user created
     *
     * @param login
     * @return true if user created , false if user didnt created
     */

    @Override
    public boolean isCreated(String login) {

        // if user equals not null - return true , else return false
        if (dao.existsByLogin(login)) {
            return true;
        }

        return false;
    }

    /**
     * check is password correct
     *
     * @param authDto
     * @return
     */

    @Override
    public boolean isCorrectPassword(AuthDto authDto) {

        User user = userService.findByLogin(authDto.getLogin());

        //if password is correct - return true
        if (user!=null && encoder.matches(user.getPassword(),authDto.getPassword())) {
            return true;
        }

        //else - return false
        return false;
    }

}



