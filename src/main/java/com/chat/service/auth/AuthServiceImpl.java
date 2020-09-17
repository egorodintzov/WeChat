package com.chat.service.auth;


import com.chat.dao.UserDao;
import com.chat.dto.AuthDto;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserDao dao;

    private static final Logger logger = Logger.getLogger(com.chat.service.auth.AuthService.class.getName());

    /**
     * check is user created
     *
     * @param login
     * @return true if user created , false if user didnt created
     */

    @Override
    public boolean checkIsCreated(String login) {

        // if user equals null - return false , else return true
        if (dao.existsByLogin(login)) {
            logger.info("user didnt created");
            return false;
        } else {
            logger.info("user created");
            return true;
        }
    }

    /**
     * check is password correct
     *
     * @param authDto
     * @return
     */

    @Override
    public boolean checkUser(AuthDto authDto) {

        //if password is correct - return true
        if (userService.findByLogin(authDto.getLogin()).getPassword().equals(authDto.getPassword()))
            return true;

        //else - return false
        return false;
    }

}



