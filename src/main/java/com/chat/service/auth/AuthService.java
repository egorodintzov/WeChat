package com.chat.service.auth;

import com.chat.dto.UserDto;
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
     * @param authDto-object which contains login and password
     * @return true if user created , false if user didnt created
     */

    @Override
    public boolean checkIsCreated(UserDto authDto) {

        // get user by login and check if user equals null - return false , else return true
        if(userService.findByLogin(authDto.getLogin())==null) {
            logger.info("user didnt created");
            return false;
        }
        else {
            logger.info("user created");
            return true;
        }
    }

}
