package com.chat.security.jwt;

import com.chat.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUser implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authority;

    public static JwtUser userToJwtUser(User user) {
        JwtUser jwtUser = new JwtUser();
        if(user!=null) {
            jwtUser.setLogin(user.getLogin());
            jwtUser.setPassword(user.getPassword());
            jwtUser.setAuthority(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        }
        return jwtUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    private void setLogin(String login) {
        this.login = login;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setAuthority(Collection<? extends GrantedAuthority> authority) {
        this.authority = authority;
    }
}
