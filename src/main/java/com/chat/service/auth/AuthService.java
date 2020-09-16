package com.chat.service.auth;

import com.chat.dto.AuthDto;
import com.chat.dto.UserDto;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @author Egor Odintsov
 */

@Service
public abstract class AuthService implements AuthServiceI {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    /**
     * check is user created
     * @param login
     * @return true if user created , false if user didnt created
     */

    @Override
    public boolean checkIsCreated(String login) {

        // get user by login and check if user equals null - return false , else return true
        if(userService.findByLogin(login)==null) {
            logger.info("user didnt created");
            return false;
        }
        else {
            logger.info("user created");
            return true;
        }
    }

    /**
     * check is password correct
     * @param authDto
     * @return
     */

    public boolean checkUser(AuthDto authDto) {

        //get user by login
        User user = userService.findByLogin(authDto.getLogin());

        //if this user exists and password is correct - return true
        if(user!=null && user.getPassword().equals(authDto.getPassword()))
           return true;
        //else - return false
        return false;
    }

}
