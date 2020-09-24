package com.chat.service.chat;

import com.chat.model.User;

import java.util.List;

public interface ChatService {

    List<String> getAllChats();

    void createChat(User user1, User user2);
}
