package com.chat.service.chat;

import com.chat.model.Chat;
import com.chat.model.User;

import java.util.List;

public interface ChatService {

    List<String> getAllChats(User currentUser);

    void createChat(User user1, User user2);

    Chat findById(long id);

    void updateMessage(Chat chat);
}
