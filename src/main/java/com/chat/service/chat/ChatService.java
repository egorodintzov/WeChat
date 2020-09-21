package com.chat.service.chat;

import java.util.List;

public interface ChatService {

    List<String> getAllChats();

    void createChat(String login);
}
