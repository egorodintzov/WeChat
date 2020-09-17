package com.chat.security;

import com.chat.exceptions.UserNotFoundException;
import com.chat.model.User;
import com.chat.security.jwt.JwtUser;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByLogin(username);
        if(user==null)
           return JwtUser.userToJwtUser(user);
        throw new UserNotFoundException("user not found");
    }
}
