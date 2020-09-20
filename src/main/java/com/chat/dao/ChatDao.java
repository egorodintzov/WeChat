package com.chat.dao;

import com.chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDao extends JpaRepository<Chat,Long> {


}
