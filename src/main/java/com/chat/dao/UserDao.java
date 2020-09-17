package com.chat.dao;

import com.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    User findById(long id);

    User findByLogin(String login);

    List<User> findAllByLoginStartingWith(String login);

    boolean existsByLogin(String login);
}
