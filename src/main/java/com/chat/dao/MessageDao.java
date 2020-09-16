package com.chat.dao;

import com.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message,Long> {

    Message findById(long id);
}
