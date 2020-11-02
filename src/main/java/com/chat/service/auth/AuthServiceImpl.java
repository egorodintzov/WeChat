package com.chat.service.auth;


import com.chat.dao.UserDao;
import com.chat.dto.AuthDto;
import com.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Egor Odintsov
 */

@Service
public class AuthServiceImpl implements AuthService {

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
    public boolean isUserExists(String login) {
        return dao.existsByLogin(login);
    }

    /**
     * check is password correct
     *
     * @param regDto object - which contains nickname,login,password
     * @param user object - current user
     * @return
     */

    @Override
    public boolean isCorrectPassword(AuthDto authDto,User user) {
        //if password is correct - return true
        return user!=null && encoder.matches(authDto.getPassword(),user.getPassword());
    }

}



