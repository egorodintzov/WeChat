package com.chat.security;

import com.chat.exceptions.UserNotFoundException;
import com.chat.model.User;
import com.chat.security.jwt.JwtUser;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserService service;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByLogin(username);
        if (user == null) {
            log.severe("User with username: " + username + " not found");
            throw new UserNotFoundException("User not found");
        }

        return JwtUser.userToJwtUser(user);
    }
}
