package com.chat.dao;

import com.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    User findByLogin(String login);

    List<User> findAllByNicknameStartingWith(String nickname);

    boolean existsByLogin(String login);
}
